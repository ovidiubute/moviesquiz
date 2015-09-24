package com.ovi.apps.movieserver.business;

import com.ovi.apps.movieserver.domain.Movie;
import com.ovi.apps.movieserver.dto.TryAnswerDto;
import com.ovi.apps.movieserver.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public int numberOfQuizQuestions() {
        return 3;
    }

    public int numberOfQuizQuestionMovies() {
        return 4;
    }

    @Override
    public boolean isAnswerCorrect(TryAnswerDto tryAnswerDto) {
        final List<Long> movieIds = Arrays.asList(tryAnswerDto.getMovieIds());
        final Iterable<Movie> dbMovies = movieRepository.findByIdOrderByReleaseYearDesc(movieIds);
        final List<Movie> movies = new ArrayList<>();
        for (Movie dbMovie : dbMovies) {
            movies.add(dbMovie);
        }

        for (int i = 0; i < movieIds.size(); i++) {
            if (!movieIds.get(i).equals(movies.get(i).getId())) {
                return false;
            }
        }

        return true;
    }
}
