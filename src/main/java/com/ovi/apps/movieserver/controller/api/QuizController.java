package com.ovi.apps.movieserver.controller.api;

import com.ovi.apps.movieserver.ResourceNotFoundException;
import com.ovi.apps.movieserver.business.QuizService;
import com.ovi.apps.movieserver.business.TimeProvider;
import com.ovi.apps.movieserver.domain.Movie;
import com.ovi.apps.movieserver.domain.Quiz;
import com.ovi.apps.movieserver.domain.User;
import com.ovi.apps.movieserver.dto.*;
import com.ovi.apps.movieserver.repository.MovieRepository;
import com.ovi.apps.movieserver.repository.QuizRepository;
import com.ovi.apps.movieserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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

    @RequestMapping(value = "/{quizId:[\\d]+}/answers", method = RequestMethod.POST)
    public ConfirmAnswerDto answer(@PathVariable @Valid Long quizId, @RequestBody @Valid TryAnswerDto tryAnswerDto, @RequestParam @Valid @Min(1) int ordinal) {
        if (ordinal > quizService.numberOfQuizQuestions()) {
            throw new IllegalArgumentException("Ordinal greater than maximum questions");
        }

        Quiz quiz = quizRepository.findOne(quizId);
        if (quiz == null) {
            throw new ResourceNotFoundException();
        } else if (quiz.getFinished()) {
            throw new IllegalStateException("Quiz already finished");
        }

        if (quiz.getAnswers() != null) {
            if (quiz.getAnswers().length >= ordinal) {
                throw new IllegalStateException("Question already answered");
            } else if (quiz.getAnswers().length - ordinal > -1) {
                throw new IllegalStateException("There are previous unanswered questions");
            }
        } else {
            if (ordinal > 1) {
                throw new IllegalStateException("There are previous unanswered questions");
            }
        }

        final boolean isAnswerCorrect = quizService.isAnswerCorrect(tryAnswerDto);
        if (isAnswerCorrect) {
            quiz.setAnswer(ordinal, true);
            quiz.incrementAndGetScore((float) 100 / quizService.numberOfQuizQuestions());
        } else {
            quiz.setAnswer(ordinal, false);
        }
        if (quiz.getAnswers().length == quizService.numberOfQuizQuestions()) {
            quiz.setFinished(true);
            quiz.setFinishTimestamp(timeProvider.getCurrentTimeMillis());
        }
        quiz = quizRepository.save(quiz);
        final QuizDto quizDto = convertToDTO(quiz);
        return new ConfirmAnswerDto(quizDto, isAnswerCorrect);
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
    public QuizDto getQuiz(@PathVariable("id") Long id) {
        final Quiz quiz = quizRepository.findOne(id);
        if (quiz != null) {
            return convertToDTO(quiz);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public QuizQuestionDto getQuestion() {
        final List<Movie> movieStream = movieRepository.findRandomMovies(new PageRequest(0,
                quizService.numberOfQuizQuestionMovies()));
        return new QuizQuestionDto(movieStream);
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

    private QuizDto convertToDTO(Quiz quiz) {
        final User owner = quiz.getOwner();
        return new QuizDto(quiz.getId(), new UserDto(owner.getId(), owner.getUsername(), owner.getEmail()),
                quiz.getFinished(), quiz.getScore(), quiz.getStartTimestamp(), quiz.getFinishTimestamp());
    }
}
