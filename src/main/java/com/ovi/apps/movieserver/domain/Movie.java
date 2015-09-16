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

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    protected Movie() {
    }

    public Movie(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format(
                "Movie[id=%d, name='%s']", id, name);
    }
}
