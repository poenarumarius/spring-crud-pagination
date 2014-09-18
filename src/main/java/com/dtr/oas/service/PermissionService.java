package com.dtr.oas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dtr.oas.exception.DuplicatePermissionException;
import com.dtr.oas.exception.PermissionNotFoundException;
import com.dtr.oas.model.Permission;


public interface PermissionService {

    public void addPermission(Permission permission) throws DuplicatePermissionException;

    public Permission getPermission(long id) throws PermissionNotFoundException;
    
    public Permission getPermission(String permissionname) throws PermissionNotFoundException;

    public void updatePermission(Permission permission) throws PermissionNotFoundException, DuplicatePermissionException;

    public void deletePermission(long id) throws PermissionNotFoundException;

    public List<Permission> getPermissions();
    
    public Page<Permission> getPermissions(Pageable pageable);

}
