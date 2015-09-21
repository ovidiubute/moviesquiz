package com.ovi.apps.movieserver.dto;

public class QuizDto {
    private Long id;

    private UserDto owner;

    private boolean finished;

    private float score;

    private long startedAt;

    private long finishedAt;

    public QuizDto(Long id, UserDto owner, boolean finished, float score, long startedAt, long finishedAt) {
        this.id = id;
        this.owner = owner;
        this.finished = finished;
        this.score = score;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    public QuizDto() {}

    public Long getId() {
        return id;
    }

    public UserDto getOwner() {
        return owner;
    }

    public boolean isFinished() {
        return finished;
    }

    public float getScore() {
        return score;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public long getFinishedAt() {
        return finishedAt;
    }
}
