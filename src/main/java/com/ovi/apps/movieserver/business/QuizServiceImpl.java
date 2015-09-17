package com.ovi.apps.movieserver.business;

import com.ovi.apps.movieserver.dto.AnswerDto;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

    @Override
    public int numberOfQuizQuestions() {
        return 10;
    }

    @Override
    public int numberOfQuizQuestionChoices() {
        return 5;
    }

    @Override
    public boolean isAnswerCorrect(AnswerDto answerDto) {
        return false;
    }
}
