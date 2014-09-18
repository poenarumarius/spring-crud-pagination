package com.dtr.oas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.dtr.oas.exception.DuplicateUserException;
import com.dtr.oas.exception.UserNotFoundException;
import com.dtr.oas.model.User;

public interface UserService extends UserDetailsService {

    public void addUser(User user) throws DuplicateUserException;

    public User getUser(long userId) throws UserNotFoundException;

    public User getUser(String username) throws UserNotFoundException;

    public void updateUser(User user) throws UserNotFoundException, DuplicateUserException;

    public void deleteUser(long userId) throws UserNotFoundException;

    public List<User> getUsers();
    
    public Page<User> getUsers(Pageable pageable);

}
