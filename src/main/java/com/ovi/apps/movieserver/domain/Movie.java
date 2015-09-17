package com.ovi.apps.movieserver.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MOVIES")
public class Movie implements Serializable {
    private static final long serialVersionUID = 3107897896655094094L;

    @Id
    @Column(name = "MOVIE_ID", nullable = false)
    @TableGenerator(name = "MOVIE_GEN",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "MOVIE_SEQUENCE",
            initialValue = 1000,
            allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MOVIE_GEN")
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "RELEASE_YEAR")
    private Integer releaseYear;

    protected Movie() {
    }

    public Movie(String name, Integer releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return String.format(
                "Movie[id=%d, name='%s', releaseYear='%d']", id, name, releaseYear);
    }
}
