package com.example.footballbackend.core.result.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {
    @Query("select r from Result r")
    Page<Result> findAllResult(Pageable pageable);

    @Query("select r from Result r where r.id = :id")
    Optional<Result> findById(@Param("id") Integer id);
}
