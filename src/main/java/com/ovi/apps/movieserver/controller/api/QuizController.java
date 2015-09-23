package com.ovi.apps.movieserver.controller.api;

import com.ovi.apps.movieserver.ResourceNotFoundException;
import com.ovi.apps.movieserver.business.QuizService;
import com.ovi.apps.movieserver.business.TimeProvider;
import com.ovi.apps.movieserver.domain.Movie;
import com.ovi.apps.movieserver.domain.Quiz;
import com.ovi.apps.movieserver.domain.User;
import com.ovi.apps.movieserver.dto.AnswerDto;
import com.ovi.apps.movieserver.dto.QuizDto;
import com.ovi.apps.movieserver.dto.QuizQuestionDto;
import com.ovi.apps.movieserver.dto.UserDto;
import com.ovi.apps.movieserver.repository.MovieRepository;
import com.ovi.apps.movieserver.repository.QuizRepository;
import com.ovi.apps.movieserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/quiz")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    private QuizService quizService;

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(value = "/answers", method = RequestMethod.POST)
    public Boolean answer(@RequestBody @Valid AnswerDto answerDto) {
        return quizService.isAnswerCorrect(answerDto);
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
    public Quiz getQuiz(@PathVariable("id") Long id) {
        final Quiz quiz = quizRepository.findOne(id);
        if (quiz != null) {
            return quiz;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public QuizQuestionDto getQuestion(@RequestParam @Valid int ordinal) {
        final List<Movie> movieStream = movieRepository.findRandomMovies(new PageRequest(0, quizService.numberOfQuizQuestionMovies()));
        return new QuizQuestionDto(ordinal, movieStream);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public QuizDto addQuiz(@RequestBody QuizDto quizDto) {
        final String username = "ovidiu";
        final User user = userRepository.findByUsername(username);
        Quiz newQuiz = new Quiz(user, timeProvider.getCurrentTimeMillis());
        final Quiz savedQuiz = quizRepository.save(newQuiz);
        final UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail());
        return new QuizDto(savedQuiz.getId(), userDto, false, 0f, timeProvider.getCurrentTimeMillis(), -1);
    }
}
