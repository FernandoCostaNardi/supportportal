package com.supportportal.service;

import com.supportportal.domain.Users;
import com.supportportal.exception.domain.EmailExistException;
import com.supportportal.exception.domain.EmailNotFoundException;
import com.supportportal.exception.domain.UsernameExistException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    Users register(String firstName, String lastName, String username, String email) throws EmailExistException, UsernameExistException, MessagingException;
    List<Users> getUsers();
    Users findUserByUsername(String username);
    Users findUserByEmail(String email);
    Users addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException;
    Users updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException;
    void deleteUser(long id);
    void resetPassword(String email) throws EmailNotFoundException, MessagingException;
    Users updateProfileImage(String username, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException;
}
