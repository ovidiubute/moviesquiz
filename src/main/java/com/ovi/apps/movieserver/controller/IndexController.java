package com.ovi.apps.movieserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @RequestMapping("/src/main/webapp/index.html")
    public String index() {
        return "WEB-INF/views/index.html";
    }

    @RequestMapping("/")
    public String indexRedirect() {
        return "redirect:index.html";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "WEB-INF/views/login.html";
    }

    @RequestMapping("/src/main/webapp/login.html")
    public String login() {
        return "WEB-INF/views/login.html";
    }

    // Error page
    @RequestMapping("/src/main/webapp/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = null;
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage);
        return "WEB-INF/views/error.html";
    }
}
