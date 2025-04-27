package com.example.footballbackend.core.staff.dto;

import com.example.footballbackend.core.team.dto.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fc_staff")
public class Staff {
    @Id
    @SequenceGenerator(name = "seq_gen_staff", sequenceName = "seq_staff", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_staff")
    @Column(name = "id")
    private Integer id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "staff_role")
    @Enumerated(EnumType.STRING)
    private RoleStaffEnum staffRole;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
