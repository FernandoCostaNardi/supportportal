package com.supportportal.service;

import com.supportportal.domain.Users;
import com.supportportal.exception.domain.EmailExistException;
import com.supportportal.exception.domain.UsernameExistException;

import java.util.List;

public interface UserService {

    Users register(String firstName, String lastName, String username, String email) throws EmailExistException, UsernameExistException;
    List<Users> getUsers();
    Users findUserByUsername(String username);

    Users findUserByEmail(String email);

}
