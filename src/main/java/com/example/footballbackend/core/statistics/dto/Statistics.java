package com.example.footballbackend.core.statistics.dto;

import com.example.footballbackend.core.match.dto.Match;
import com.example.footballbackend.core.player.dto.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fc_player_statistics")
public class Statistics {
    @Id
    @SequenceGenerator(name = "seq_gen_statistics", sequenceName = "seq_statistics", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_statistics")
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;

    @Column(name = "goals")
    private int goals;

    @Column(name = "assists")
    private int assists;

    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "red_cards")
    private int redCards;

    @Column(name = "played_minutes")
    private int playedMinutes;

    @Column(name = "shots")
    private int shots;

    @Column(name = "passes")
    private int passes;

    @Column(name = "season")
    private LocalDate season;
}
