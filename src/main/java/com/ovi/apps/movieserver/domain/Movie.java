package com.ovi.apps.movieserver.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MOVIES")
public class Movie implements Serializable {
    private static final long serialVersionUID = 3107897896655094094L;

    @Id
    @TableGenerator(name = "MOVIE_GEN",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "MOVIE_SEQUENCE",
            initialValue = 1000,
            allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MOVIE_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RATING")
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
