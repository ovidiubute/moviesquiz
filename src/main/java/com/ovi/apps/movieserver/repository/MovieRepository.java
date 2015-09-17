package com.ovi.apps.movieserver.repository;

import com.ovi.apps.movieserver.domain.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

}
