package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//import com.dtr.oas.dao.PermissionDAO;
import com.dtr.oas.exception.DuplicatePermissionException;
import com.dtr.oas.exception.PermissionNotFoundException;
import com.dtr.oas.model.Permission;
import com.dtr.oas.repository.PermissionRepository;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    static Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    
//    @Autowired
//    private PermissionDAO permissionDAO;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void addPermission(Permission permission) throws DuplicatePermissionException {
//        permissionDAO.addPermission(permission);
        permissionRepository.save(permission);
    }

    @Override
    public Permission getPermission(long id) throws PermissionNotFoundException {
//        return permissionDAO.getPermission(id);
        return permissionRepository.findOne(id);
    }

    @Override
    public Permission getPermission(String permissionname) throws PermissionNotFoundException {
//        return permissionDAO.getPermission(permissionname);
        return permissionRepository.findByPermissionname(permissionname);
    }

    @Override
    public void updatePermission(Permission permission) throws PermissionNotFoundException, DuplicatePermissionException {
//        permissionDAO.updatePermission(permission);
        permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(long id) throws PermissionNotFoundException {
//        permissionDAO.deletePermission(id);
        permissionRepository.delete(id);
    }

    @Override
    public List<Permission> getPermissions() {
//        return permissionDAO.getPermissions();
        return permissionRepository.findAll();
    }

	@Override
	public Page<Permission> getPermissions(Pageable pageable) {
		return permissionRepository.findAll(pageable);
	}

}
