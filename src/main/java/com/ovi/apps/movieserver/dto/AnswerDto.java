package com.ovi.apps.movieserver.dto;

import org.hibernate.validator.constraints.NotEmpty;

@Dto
public final class AnswerDto {

    @NotEmpty
    private Long[] movieIds;

    @NotEmpty
    private Integer ordinal;

    public AnswerDto() {
    }

    public AnswerDto(Long[] movieIds, Integer ordinal) {
        this.movieIds = movieIds;
        this.ordinal = ordinal;
    }

    public Long[] getMovieIds() {
        return movieIds;
    }

    public Integer getOrdinal() {
        return ordinal;
    }
}
