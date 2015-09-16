package com.ovi.apps.movieserver.dto;

import org.hibernate.validator.constraints.NotEmpty;

@Dto
public final class MovieDto {
    private Long id;

    @NotEmpty
    private String name;

    public MovieDto() {
    }

    public MovieDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
