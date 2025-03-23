package com.example.footballbackend.core.role.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "fc_role_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"role_name"}),
        @UniqueConstraint(columnNames = {"description"})
})
public class Role {
    @Id
    @SequenceGenerator(name = "seq_gen_role", sequenceName = "seq_role", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_role")
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
