package com.ovi.apps.movieserver.dto;

import java.util.LinkedList;
import java.util.List;

public class QuizQuestionDto {
    private Integer ordinal;

    private LinkedList<MovieDto> movies;

    public QuizQuestionDto(Integer ordinal, List<MovieDto> movies) {
        this.ordinal = ordinal;
        this.movies = new LinkedList<>();
        movies.stream().forEach(this.movies::push);
    }

    public LinkedList<MovieDto> getMovies() {
        return movies;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    @Override
    public String toString() {
        return String.format("QuizQuestionDto[ordinal='%d', movies='%s']", ordinal, movies);
    }
}
