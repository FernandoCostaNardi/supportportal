package com.supportportal.service;

import com.supportportal.domain.Users;
import com.supportportal.exception.domain.EmailExistException;
import com.supportportal.exception.domain.UsernameExistException;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

    Users register(String firstName, String lastName, String username, String email) throws EmailExistException, UsernameExistException, MessagingException;
    List<Users> getUsers();
    Users findUserByUsername(String username);

    Users findUserByEmail(String email);

}
