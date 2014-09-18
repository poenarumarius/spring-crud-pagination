package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//import com.dtr.oas.dao.RoleDAO;
import com.dtr.oas.exception.DuplicateRoleException;
import com.dtr.oas.exception.RoleNotFoundException;
import com.dtr.oas.model.Role;
import com.dtr.oas.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    
//    @Autowired
//    private RoleDAO roleDAO;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) throws DuplicateRoleException {
//        roleDAO.addRole(role);
        roleRepository.save(role);
    }

    @Override
    public Role getRole(long id) throws RoleNotFoundException {
//        return roleDAO.getRole(id);
        return roleRepository.findOne(id);
    }

    @Override
    public Role getRole(String rolename) throws RoleNotFoundException {
//        return roleDAO.getRole(rolename);
        return roleRepository.findByRolename(rolename);
    }

    @Override
    public void updateRole(Role role) throws RoleNotFoundException, DuplicateRoleException {
//        roleDAO.updateRole(role);
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(long id) throws RoleNotFoundException {
//        roleDAO.deleteRole(id);
        roleRepository.delete(id);
    }

    @Override
    public List<Role> getRoles() {
//        return roleDAO.getRoles();
        return roleRepository.findAll();
    }

	@Override
	public Page<Role> getRoles(Pageable pageable) {
		// TODO Auto-generated method stub
		return roleRepository.findAll(pageable);
	}
    
}
