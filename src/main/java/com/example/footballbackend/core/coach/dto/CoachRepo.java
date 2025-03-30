package com.example.footballbackend.core.coach.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepo extends JpaRepository<Coach, Integer> {
    @EntityGraph(attributePaths = {"team"})
    @Query("select c from Coach c")
    Page<Coach> findAllCoach(Pageable pageable);

    @EntityGraph(attributePaths = {"team"})
    @Query("select c from Coach c where c.id = :id")
    Optional<Coach> findById(@Param("id") Integer id);
}
