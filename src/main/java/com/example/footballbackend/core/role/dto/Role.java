package com.example.footballbackend.core.role.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "fc_role_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"role_name"}),
        @UniqueConstraint(columnNames = {"description"})
})
public class Role implements GrantedAuthority {
    @Id
    @SequenceGenerator(name = "seq_gen_role", sequenceName = "seq_role", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_role")
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;


    @Override
    public String getAuthority() {
        return roleName;
    }
}
