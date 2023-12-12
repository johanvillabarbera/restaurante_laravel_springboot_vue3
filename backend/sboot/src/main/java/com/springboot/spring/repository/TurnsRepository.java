package com.springboot.spring.repository;

import com.springboot.spring.model.Turns;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnsRepository extends JpaRepository<Turns, Long> {
     List<Turns> findByMeal(String meal);
}