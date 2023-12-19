package com.springboot.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.spring.model.UserEventTable;

public interface UserEventTableRepository extends JpaRepository<UserEventTable, Long> {


    
}