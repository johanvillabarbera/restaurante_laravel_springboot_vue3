package com.springboot.spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.springboot.spring.model.UnverifiedUser;

public interface UnverifiedUserRepository extends JpaRepository<UnverifiedUser, Long> {

    @Query(value = "SELECT COUNT(*) FROM unverified_users WHERE username = :username", nativeQuery = true)
    Integer existsByUsername(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM unverified_users WHERE tmp_token = :tmp_token", nativeQuery = true)
    Integer existsByTmpToken(@Param("tmp_token") String tmp_token);

    @Query("SELECT u FROM UnverifiedUser u WHERE u.tmp_token = :tmp_token")
    Optional<UnverifiedUser> findByTmpToken(@Param("tmp_token") String tmp_token);
}