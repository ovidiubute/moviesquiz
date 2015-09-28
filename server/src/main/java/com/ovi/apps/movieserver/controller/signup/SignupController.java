/**
 * Copyright (c) 2015 Ovidiu Bute
 * All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */
package com.ovi.apps.movieserver.controller.signup;

import com.ovi.apps.movieserver.account.Account;
import com.ovi.apps.movieserver.account.AccountRepository;
import com.ovi.apps.movieserver.account.UsernameAlreadyInUseException;
import com.ovi.apps.movieserver.message.Message;
import com.ovi.apps.movieserver.message.MessageType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class SignupController {
    private final AccountRepository accountRepository;
    private final ProviderSignInUtils providerSignInUtils;

    @Inject
    public SignupController(AccountRepository accountRepository,
                            ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository) {
        this.accountRepository = accountRepository;
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public SignupForm signupForm(WebRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            request.setAttribute("message", new Message(MessageType.INFO, "Your "
                    + StringUtils.capitalize(connection.getKey().getProviderId())
                    + " account is not associated with an account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
            return SignupForm.fromProviderUser(connection.fetchUserProfile());
        } else {
            return new SignupForm();
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
        if (formBinding.hasErrors()) {
            return null;
        }
        Account account = createAccount(form, formBinding);
        if (account != null) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(account.getUsername(), null, null));
            providerSignInUtils.doPostSignUp(account.getUsername(), request);
            return "redirect:/";
        }
        return null;
    }

    private Account createAccount(SignupForm form, BindingResult formBinding) {
        try {
            Account account = new Account(form.getUsername(), form.getPassword(), form.getFirstName(), form.getLastName());
            accountRepository.createAccount(account);
            return account;
        } catch (UsernameAlreadyInUseException e) {
            formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
            return null;
        }
    }
}
