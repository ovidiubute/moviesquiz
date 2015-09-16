package com.ovi.apps.movieserver.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Quiz implements Serializable {
    private static final long serialVersionUID = 3207557896655494094L;

    @Id
    @TableGenerator(name = "QUIZ_GEN",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "QUIZ_SEQUENCE",
            initialValue = 1000,
            allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "QUIZ_GEN")
    @Column(name = "ID")
    private Long id;

    @ManyToOne(optional = false)
    private User owner;

    @Column(name = "START_TIMESTAMP", nullable = false)
    private Long startTimestamp;

    @Column(name = "FINISHED", nullable = false)
    private Boolean finished;

    protected Quiz() {}
}
