package com.ovi.apps.movieserver.dto;

import org.hibernate.validator.constraints.NotEmpty;

@Dto
public final class AnswerDto {

    @NotEmpty
    private Long[] movieIds;

    public AnswerDto() {
    }

    public AnswerDto(Long[] movieIds) {
        this.movieIds = movieIds;
    }

    public Long[] getMovieIds() {
        return movieIds;
    }
}
