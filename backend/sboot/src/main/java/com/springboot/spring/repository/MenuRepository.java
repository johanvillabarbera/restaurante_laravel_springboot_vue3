package com.springboot.spring.repository;

import com.springboot.spring.model.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
     List<Menu> findByPrice(Integer price);
}
