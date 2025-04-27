package com.example.footballbackend.core.match.dto;

import com.example.footballbackend.core.statistics.dto.Statistics;
import com.example.footballbackend.core.team.dto.Team;
import com.example.footballbackend.core.tournament.dto.Tournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fc_match")
public class Match {
    @Id
    @SequenceGenerator(name = "seq_gen_match", sequenceName = "seq_match", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_match")
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    private Team awayTeam;

    @Column(name = "score")
    private String score;

    @Column(name = "location")
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_status")
    private MatchStatusEnum status;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @OneToMany(mappedBy = "match")
    private List<Statistics> playerStats;
}
