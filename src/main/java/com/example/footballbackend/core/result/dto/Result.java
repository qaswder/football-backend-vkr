package com.example.footballbackend.core.result.dto;

import com.example.footballbackend.core.team.dto.Team;
import com.example.footballbackend.core.tournament.dto.Tournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fc_results")
public class Result {
    @Id
    @SequenceGenerator(name = "seq_gen_results", sequenceName = "seq_results", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_results")
    @Column(name = "id")
    private Integer id;

    @Column(name = "position")
    private int position;

    @Column(name = "played")
    private int played;

    @Column(name = "wins")
    private int wins;

    @Column(name = "draws")
    private int draws;

    @Column(name = "losses")
    private int losses;

    @Column(name = "points")
    private int points;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
