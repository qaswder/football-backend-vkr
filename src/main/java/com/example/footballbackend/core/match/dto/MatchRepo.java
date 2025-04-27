package com.example.footballbackend.core.match.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepo extends JpaRepository<Match, Integer> {

    @EntityGraph(attributePaths = {"homeTeam", "awayTeam"})
    @Query("select m from Match m")
    Page<Match> findAllMatch(Pageable pageable);

    @EntityGraph(attributePaths = {"homeTeam", "awayTeam"})
    @Query("select m from Match m where m.id = :id")
    Optional<Match> findById(@Param("id") Integer id);
}
