/**
 * Copyright (c) 2015 Ovidiu Bute
 * All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */
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
