package com.springboot.spring.repository;

import com.springboot.spring.model.Tables;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Tables, Long> {
  List<Tables> findByCapacity(int capacity);
}
