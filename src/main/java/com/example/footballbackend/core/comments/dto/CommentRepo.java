package com.example.footballbackend.core.comments.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    @Query("select c from Comment c")
    Page<Comment> findAllComments(Pageable pageable);

    @Query("select c from Comment c where c.id = :id")
    Optional<Comment> findById(@Param("id") Integer id);
}
