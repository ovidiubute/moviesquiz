package com.ovi.apps.movieserver.controller.api;

import com.ovi.apps.movieserver.ResourceNotFoundException;
import com.ovi.apps.movieserver.domain.Movie;
import com.ovi.apps.movieserver.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable("id") Long id) {
        final Movie movie = movieRepository.findOne(id);
        if (movie != null) {
            return movie;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    HttpEntity<PagedResources<Movie>> getAllMovies(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Movie> movies = movieRepository.findAll(pageable);

        return new ResponseEntity<>(assembler.toResource(movies), HttpStatus.OK);
    }
}
