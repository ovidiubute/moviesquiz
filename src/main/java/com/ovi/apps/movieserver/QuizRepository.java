package com.ovi.apps.movieserver;

import com.ovi.apps.movieserver.domain.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    List<Quiz> findByOwnerId(Long userId);
}
