package com.ovi.apps.movieserver.business;

import com.ovi.apps.movieserver.dto.AnswerDto;

public interface QuizService {
    int numberOfQuizQuestions();

    int numberOfQuizQuestionChoices();

    boolean isAnswerCorrect(AnswerDto answerDto);
}
