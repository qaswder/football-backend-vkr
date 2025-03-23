package com.example.footballbackend.core.role.dto;

import com.example.footballbackend.core.user.dto.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Query("select r from Role r")
    Page<Role> findAllRole(Pageable pageable);

    @Query("select r from Role r where r.id = :id")
    Optional<Role> findById(@Param("id") Integer id);

    @Query("select r from Role r where r.description = :description")
    Optional<Role> findRoleByDescription(@Param("description") String description);

    @Query("select case when count(r) > 0 then true else false end from Role r where r.roleName = :roleName or r.description = :description")
    Boolean existsByRoleNameOrDescription(@Param("roleName") String roleName, @Param("description") String description);

}
