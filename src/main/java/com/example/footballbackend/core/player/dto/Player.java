package com.example.footballbackend.core.player.dto;

import com.example.footballbackend.core.statistics.dto.Statistics;
import com.example.footballbackend.core.team.dto.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fc_player")
public class Player {
    @Id
    @SequenceGenerator(name = "seq_gen_player", sequenceName = "seq_player", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_player")
    @Column(name = "id")
    private Integer id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_date")
    private LocalDate birthdate;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "player_number")
    private Integer playerNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private PositionEnum position;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<Statistics> playerStats;
}
