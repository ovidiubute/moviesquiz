package com.ovi.apps.movieserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "QUIZES")
public class Quiz implements Serializable {
    private static final long serialVersionUID = 3207557896655494094L;

    @Id
    @Column(name = "QUIZ_ID", nullable = false)
    @TableGenerator(name = "QUIZ_GEN",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "QUIZ_SEQUENCE",
            initialValue = 1000,
            allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "QUIZ_GEN")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User owner;

    @Column(name = "START_TIMESTAMP", nullable = false)
    private Long startTimestamp;

    @Column(name = "FINISH_TIMESTAMP", nullable = true)
    private Long finishTimestamp;

    @Column(name = "FINISHED", nullable = false)
    private Boolean finished;

    @Column(name = "SCORE", nullable = true)
    private Float score;

    @Column(name = "ANSWERS", nullable = true)
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] answers;

    protected Quiz() {
    }

    public Quiz(User owner, Long startTimestamp) {
        Objects.requireNonNull(owner);
        Objects.requireNonNull(startTimestamp);

        this.owner = owner;
        this.startTimestamp = startTimestamp;
    }

    public Long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public Long getFinishTimestamp() {
        return finishTimestamp;
    }

    public Boolean getFinished() {
        return finished;
    }

    public Float getScore() {
        return score;
    }

    public byte[] getAnswers() {
        return answers;
    }
}
