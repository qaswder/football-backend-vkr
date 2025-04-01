package com.example.footballbackend.core.statistics.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepo extends JpaRepository<Statistics, Integer> {
    @EntityGraph(attributePaths = {"player"})
    @Query("select s from Statistics s")
    Page<Statistics> findAllStatistics(Pageable pageable);

    @EntityGraph(attributePaths = {"player"})
    @Query("select s from Statistics s where s.id = :id")
    Optional<Statistics> findById(@Param("id") Integer id);
}
