package com.ovi.apps.movieserver.repository;

import com.ovi.apps.movieserver.domain.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.id IN :movieIds ORDER BY m.releaseYear DESC")
    List<Movie> findByIdOrderByReleaseYearDesc(@Param("movieIds") List<Long> movieIds);

    @Query("SELECT m FROM Movie m ORDER BY random()")
    List<Movie> findRandomMovies(Pageable pageable);
}
