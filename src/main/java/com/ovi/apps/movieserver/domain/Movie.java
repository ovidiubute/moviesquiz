package com.ovi.apps.movieserver.domain;

import javax.persistence.*;

@Entity
public class Movie {

    @Id
    @TableGenerator(name = "movie_id", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "movie_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Rating rating;

    protected Movie() {
    }

    public Movie(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format(
                "Movie[id=%d, name='%s', rating='%s']",
                id, name, rating.name());
    }

    public enum Rating {
        UNRATED,
        G,
        PG,
        PG13,
        R,
        NC17
    }
}
