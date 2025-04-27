package com.example.footballbackend.core.tournament.dto;

import com.example.footballbackend.core.match.dto.Match;
import com.example.footballbackend.core.result.dto.Result;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fc_tournament")
public class Tournament {
    @Id
    @SequenceGenerator(name = "seq_gen_tournament", sequenceName = "seq_tournament", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_tournament")
    @Column(name = "id")
    private Integer id;

    @Column(name = "tournament_name")
    private String tournamentName;

    @Column(name = "season")
    private String season;

    @OneToMany(mappedBy = "tournament")
    private List<Match> matches;

    @OneToMany(mappedBy = "tournament")
    private List<Result> resultTables;
}
