package com.example.footballbackend.core.schedule.dto;

import com.example.footballbackend.core.match.dto.Match;
import jakarta.persistence.*;

@Entity
@Table(name = "fc_schedule")
public class Schedule {
    @Id
    @SequenceGenerator(name = "seq_gen_schedule", sequenceName = "seq_schedule", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_schedule")
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;

    @Column(name = "status_match")
    private String statusMatch;

    ////////////////////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getStatusMatch() {
        return statusMatch;
    }

    public void setStatusMatch(String statusMatch) {
        this.statusMatch = statusMatch;
    }
}
