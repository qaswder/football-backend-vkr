package com.example.footballbackend.core.team.dto;

import com.example.footballbackend.core.player.dto.Player;
import com.example.footballbackend.core.staff.dto.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

    @Column(name = "team_short_name")
    private String teamShortName;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "logo_url")
    private String logoUrl;

    @OneToMany(mappedBy = "team")
    private List<Staff> staff;

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
