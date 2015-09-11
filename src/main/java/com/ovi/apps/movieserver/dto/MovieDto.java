package com.ovi.apps.movieserver.dto;

import org.hibernate.validator.constraints.NotEmpty;

@Dto
public final class MovieDto {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String rating;

    public MovieDto() {
    }

    public MovieDto(Long id, String name, String rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }
}
