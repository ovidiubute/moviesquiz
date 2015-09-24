package com.ovi.apps.movieserver.dto;

import org.hibernate.validator.constraints.NotEmpty;

@Dto
public final class TryAnswerDto {

    @NotEmpty
    private Long[] movieIds;

    public TryAnswerDto() {
    }

    public TryAnswerDto(Long[] movieIds) {
        this.movieIds = movieIds;
    }

    public Long[] getMovieIds() {
        return movieIds;
    }
}
