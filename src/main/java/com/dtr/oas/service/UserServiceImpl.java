package com.dtr.oas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//import com.dtr.oas.dao.UserDAO;
import com.dtr.oas.exception.DuplicateUserException;
import com.dtr.oas.exception.UserNotFoundException;
import com.dtr.oas.model.User;
import com.dtr.oas.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
//    @Autowired
//    private UserDAO userDAO;
    
    @Autowired
    private UserRepository userRepository;

	@Override
	public void addUser(User user) throws DuplicateUserException {
//		userDAO.addUser(user);
		userRepository.save(user);
	}

    @Override
    public User getUser(long userId) throws UserNotFoundException {
//        return userDAO.getUser(userId);
        return userRepository.findOne(userId);
    }

	@Override
	public User getUser(String username) throws UserNotFoundException {
//		return userDAO.getUser(username);
		return userRepository.findByUsername(username);
	}

	@Override
	public void updateUser(User user) throws UserNotFoundException, DuplicateUserException {
//		userDAO.updateUser(user);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(long userId) throws UserNotFoundException {
//		userDAO.deleteUser(userId);
		userRepository.delete(userId);
	}

	@Override
	public List<User> getUsers() {
//		return userDAO.getUsers();
		return userRepository.findAll();
	}
	
	@Override
	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return getUser(username);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

	
}
