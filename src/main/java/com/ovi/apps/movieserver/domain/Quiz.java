/**
 * Copyright (c) 2015 Ovidiu Bute
 * All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */
package com.ovi.apps.movieserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
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
    private Long finishTimestamp = -1L;

    @Column(name = "FINISHED", nullable = false)
    private Boolean finished = false;

    @Column(name = "SCORE", nullable = true)
    private Float score = 0.0f;

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

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public void setFinishTimestamp(Long finishTimestamp) {
        this.finishTimestamp = finishTimestamp;
    }

    public void setAnswer(int ordinal, boolean answer) {
        if (answers == null) {
            answers = new byte[] {(byte) ((answer) ? 1 : 0)};
        } else {
            if (answers.length < ordinal) {
                answers = Arrays.copyOf(answers, ordinal);
            }
            answers[ordinal - 1] = (byte) ((answer) ? 1 : 0);
        }
    }

    public float incrementAndGetScore(float inc) {
        score += inc;
        return score;
    }
}
