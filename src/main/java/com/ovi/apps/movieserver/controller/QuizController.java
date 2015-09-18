package com.ovi.apps.movieserver.controller;

import com.ovi.apps.movieserver.ResourceNotFoundException;
import com.ovi.apps.movieserver.domain.Quiz;
import com.ovi.apps.movieserver.dto.QuizDto;
import com.ovi.apps.movieserver.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/quiz")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Quiz getQuiz(@PathVariable("id") Long id) {
        final Quiz quiz = quizRepository.findOne(id);
        if (quiz != null) {
            return quiz;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public QuizDto addQuiz(@RequestBody QuizDto quizDto) {
        return new QuizDto();
    }
}
