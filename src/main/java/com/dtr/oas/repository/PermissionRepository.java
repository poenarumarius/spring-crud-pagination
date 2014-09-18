package com.dtr.oas.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dtr.oas.model.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query(value = "SELECT u FROM Permission u WHERE u.permissionname = :permissionname")
	Permission findByPermissionname(@Param("permissionname") String permissionname);
    
    @Query(value = "SELECT u FROM Permission u ORDER BY u.permissionname")
    Page<Permission> findAll(Pageable pageable);
    
}
