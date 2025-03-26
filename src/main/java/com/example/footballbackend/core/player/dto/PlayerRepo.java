package com.example.footballbackend.core.player.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    @Query("select p from Player p")
    Page<Player> findAllPlayer(Pageable pageable);

    @Query("select p from Player p where p.id = :id")
    Optional<Player> findById(@Param("id") Integer id);

    @Query("select p from Player p where " +
            "p.surname like %:searchTerm% or " +
            "p.name like %:searchTerm% or " +
            "p.patronymic like %:searchTerm%")
    Page<Player> findPlayerByName(@Param("searchTerm") String searchTerm, Pageable pageable);

    /*@Query("select u from User u join u.userRole r where r.description = :description")
    Page<User> findUsersByRole(@Param("description") String description, Pageable pageable);*/
}
