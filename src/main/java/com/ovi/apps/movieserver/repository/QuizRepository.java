package com.ovi.apps.movieserver.repository;

import com.ovi.apps.movieserver.domain.Quiz;
import com.ovi.apps.movieserver.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    List<Quiz> findByOwner(User owner);
}
