package com.example.footballbackend.core.tournament.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Integer> {
    @Query("select t from Tournament t")
    Page<Tournament> findAllTournament(Pageable pageable);

    @Query("select t from Tournament t where t.id = :id")
    Optional<Tournament> findById(@Param("id") Integer id);
}
