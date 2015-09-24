package com.ovi.apps.movieserver.dto;

@Dto
public class ConfirmAnswerDto extends QuizDto {
    private boolean isLastAnswerOK;

    public ConfirmAnswerDto() {}

    public ConfirmAnswerDto(QuizDto quizDto, boolean isLastAnswerOK) {
        super(quizDto.getId(), quizDto.getOwner(), quizDto.isFinished(), quizDto.getScore(), quizDto.getStartedAt(), quizDto.getFinishedAt());
        this.isLastAnswerOK = isLastAnswerOK;
    }

    public boolean isLastAnswerOK() {
        return isLastAnswerOK;
    }

    public void setIsLastAnswerOK(boolean isLastAnswerOK) {
        this.isLastAnswerOK = isLastAnswerOK;
    }
}
