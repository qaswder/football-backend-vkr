package com.example.footballbackend.core.user.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("select u from User u")
    Page<User> findAllUser(Pageable pageable);

    @Query("select u from User u where u.id = :id")
    Optional<User> findById(@Param("id") Integer id);

    @Query("select u from User u where u.username like %:searchTerm%")
    Page<User> findBookByUsername(@Param("searchTerm") String searchTerm, Pageable pageable);
}
