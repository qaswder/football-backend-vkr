package com.example.footballbackend.core.coach.dto;

import com.example.footballbackend.core.team.dto.Team;
import jakarta.persistence.*;

@Entity
@Table(name = "fc_coach")
public class Coach {
    @Id
    @SequenceGenerator(name = "seq_gen_coach", sequenceName = "seq_coach", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_coach")
    @Column(name = "id")
    private Integer id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @OneToOne(mappedBy = "coach")
    private Team team;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
