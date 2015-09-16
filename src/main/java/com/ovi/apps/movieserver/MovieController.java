package com.ovi.apps.movieserver;

import com.ovi.apps.movieserver.domain.Movie;
import com.ovi.apps.movieserver.dto.DtoFactory;
import com.ovi.apps.movieserver.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {

    @Autowired
    private DtoFactory dtoFactory;

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MovieDto getMovie(@PathVariable("id") Long id) {
        final Movie movie = movieRepository.findOne(id);
        if (movie != null) {
            return dtoFactory.movieDto(movie);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
