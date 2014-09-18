package com.dtr.oas.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dtr.oas.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT u FROM Role u WHERE u.rolename = :rolename")
    Role findByRolename(@Param("rolename") String rolename);
    
    Page<Role> findAll(Pageable pageable);
    
}
