package com.example.footballbackend.core.team.dto;

import com.example.footballbackend.core.coach.dto.Coach;
import com.example.footballbackend.core.player.dto.Player;
import com.example.footballbackend.core.user.dto.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "fc_team")
public class Team {
    @Id
    @SequenceGenerator(name = "seq_gen_team", sequenceName = "seq_team", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_team")
    @Column(name = "id")
    private Integer id;

    @Column(name = "team_name")
    private String teamName;

    @OneToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;

    @Column(name = "league")
    private String league;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    ////////////////////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
