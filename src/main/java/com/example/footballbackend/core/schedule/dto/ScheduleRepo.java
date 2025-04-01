package com.example.footballbackend.core.schedule.dto;

import com.example.footballbackend.core.coach.dto.Coach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    @EntityGraph(attributePaths = {"match"})
    @Query("select s from Schedule s")
    Page<Schedule> findAllSchedule(Pageable pageable);

    @EntityGraph(attributePaths = {"match"})
    @Query("select s from Schedule s where s.id = :id")
    Optional<Schedule> findById(@Param("id") Integer id);
}
