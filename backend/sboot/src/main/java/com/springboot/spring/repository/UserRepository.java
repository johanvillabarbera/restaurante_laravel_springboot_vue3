package com.springboot.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.spring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM clients WHERE username = :username", nativeQuery = true)
    Integer existsByUsername(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM clients WHERE email = :email", nativeQuery = true)
    Integer existsByEmail(@Param("email") String email);

    // Exists tlf
    @Query(value = "SELECT COUNT(*) FROM clients WHERE tlf = :tlf", nativeQuery = true)
    Integer existsByTlf(@Param("tlf") String tlf);
}