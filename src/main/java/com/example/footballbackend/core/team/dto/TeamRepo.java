package com.example.footballbackend.core.team.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
    @Query("select t from Team t")
    Page<Team> findAllTeam(Pageable pageable);

    @Query("select t from Team t where t.id = :id")
    Optional<Team> findById(@Param("id") Integer id);
}
