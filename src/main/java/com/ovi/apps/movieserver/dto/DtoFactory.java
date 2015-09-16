package com.ovi.apps.movieserver.dto;

import com.ovi.apps.movieserver.domain.Movie;
import org.springframework.stereotype.Component;

@Component
public class DtoFactory {
    public MovieDto movieDto(Movie domainMovie) {
        return new MovieDto(domainMovie.getId(), domainMovie.getName());
    }
}
