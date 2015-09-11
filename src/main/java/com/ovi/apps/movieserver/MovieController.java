package com.ovi.apps.movieserver;

import com.ovi.apps.movieserver.domain.DomainFactory;
import com.ovi.apps.movieserver.domain.Movie;
import com.ovi.apps.movieserver.dto.DtoFactory;
import com.ovi.apps.movieserver.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {

    @Autowired
    private DtoFactory dtoFactory;

    @Autowired
    private DomainFactory domainFactory;

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

    @RequestMapping(method = RequestMethod.POST)
    public MovieDto saveMovie(@Valid @RequestBody MovieDto movieDto) {
        return dtoFactory.movieDto(movieRepository.save(domainFactory.fromDto(movieDto)));
    }
}
