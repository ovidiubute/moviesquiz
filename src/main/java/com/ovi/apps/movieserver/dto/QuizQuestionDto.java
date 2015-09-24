package com.ovi.apps.movieserver.dto;

import com.ovi.apps.movieserver.domain.Movie;

import java.util.LinkedList;
import java.util.List;

@Dto
public class QuizQuestionDto {
    private LinkedList<Movie> movies;

    public QuizQuestionDto(List<Movie> movies) {
        this.movies = new LinkedList<>();
        movies.stream().forEach(this.movies::push);
    }

    public LinkedList<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return String.format("QuizQuestionDto[movies='%s']", movies);
    }
}
