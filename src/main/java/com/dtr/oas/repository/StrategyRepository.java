package com.dtr.oas.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dtr.oas.model.Strategy;

public interface StrategyRepository extends JpaRepository<Strategy, Long> {

    @Query(value = "SELECT u FROM Strategy u WHERE u.name = :name")
	Strategy findByName(@Param("name") String name);
    
    Page<Strategy> findAll(Pageable pageable);

}
