package com.ovi.apps.movieserver.dto;

import org.hibernate.validator.constraints.NotEmpty;

@Dto
public final class MovieDto implements Comparable<MovieDto> {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private Integer releaseYear;

    public MovieDto() {
    }

    public MovieDto(Long id, String name, Integer releaseYear) {
        this.id = id;
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
    public int compareTo(MovieDto o) {
        return Integer.compare(releaseYear, o.getReleaseYear());
    }
}
