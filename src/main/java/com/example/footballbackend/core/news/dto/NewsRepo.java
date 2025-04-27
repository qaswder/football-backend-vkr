package com.example.footballbackend.core.news.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepo extends JpaRepository<News, Integer> {

    @Query("select n from News n")
    Page<News> findAllNews(Pageable pageable);

    @Query("select n from News n where n.id = :id")
    Optional<News> findById(@Param("id") Integer id);

}
