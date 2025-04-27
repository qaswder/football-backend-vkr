package com.example.footballbackend.core.staff.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Integer> {
    @EntityGraph(attributePaths = {"team"})
    @Query("select s from Staff s")
    Page<Staff> findAllStaff(Pageable pageable);

    @EntityGraph(attributePaths = {"team"})
    @Query("select s from Staff s where s.id = :id")
    Optional<Staff> findById(@Param("id") Integer id);
}
