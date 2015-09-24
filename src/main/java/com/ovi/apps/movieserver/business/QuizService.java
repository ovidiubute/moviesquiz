package com.ovi.apps.movieserver.business;

import com.ovi.apps.movieserver.dto.TryAnswerDto;

public interface QuizService {
    int numberOfQuizQuestions();

    boolean isAnswerCorrect(TryAnswerDto tryAnswerDto);

    int numberOfQuizQuestionMovies();
}
