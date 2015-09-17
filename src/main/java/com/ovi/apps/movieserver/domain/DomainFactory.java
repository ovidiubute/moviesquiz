package com.ovi.apps.movieserver.domain;

import com.ovi.apps.movieserver.dto.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class DomainFactory {
    public Movie fromDto(MovieDto movieDto) {
        return new Movie(movieDto.getName(), movieDto.getReleaseYear());
    }
}
