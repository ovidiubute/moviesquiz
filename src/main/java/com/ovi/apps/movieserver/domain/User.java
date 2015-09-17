package com.ovi.apps.movieserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 3107897822075094094L;

    @Id
    @Column(name = "USER_ID", nullable = false)
    @TableGenerator(name = "USER_GEN",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "USER_SEQUENCE",
            initialValue = 1000,
            allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "owner", targetEntity = Quiz.class, fetch = FetchType.EAGER)
    private List<Quiz> quizes;

    protected User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s']", id, username);
    }
}
