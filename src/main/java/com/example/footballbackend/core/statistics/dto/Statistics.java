package com.example.footballbackend.core.statistics.dto;

import com.example.footballbackend.core.player.dto.Player;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fc_player_statistics", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"player_id"}),
        @UniqueConstraint(columnNames = {"season"})
})
public class Statistics {
    @Id
    @SequenceGenerator(name = "seq_gen_statistics", sequenceName = "seq_statistics", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_statistics")
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @Column(name = "goals")
    private int goals;

    @Column(name = "assists")
    private int assists;

    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "red_cards")
    private int redCards;

    @Column(name = "season")
    private LocalDate season;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public LocalDate getSeason() {
        return season;
    }

    public void setSeason(LocalDate season) {
        this.season = season;
    }
}
