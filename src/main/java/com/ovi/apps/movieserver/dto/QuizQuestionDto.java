package com.ovi.apps.movieserver.dto;

import com.ovi.apps.movieserver.domain.Movie;

import java.util.LinkedList;
import java.util.List;

@Dto
public class QuizQuestionDto {
    private Integer ordinal;

    private LinkedList<Movie> movies;

    public QuizQuestionDto(Integer ordinal, List<Movie> movies) {
        this.ordinal = ordinal;
        this.movies = new LinkedList<>();
        movies.stream().forEach(this.movies::push);
    }

    public LinkedList<Movie> getMovies() {
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
