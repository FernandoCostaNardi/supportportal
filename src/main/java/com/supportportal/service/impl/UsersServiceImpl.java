package com.supportportal.service.impl;

import com.supportportal.domain.UserPrincipal;
import com.supportportal.domain.Users;
import com.supportportal.repository.UsersRepository;
import com.supportportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@RequiredArgsConstructor
@Service
@Transactional
@Qualifier("UserDetailsService")
public class UsersServiceImpl implements UserService, UserDetailsService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findUsersByUsername(username);
        if(user == null) {
            LOGGER.error("User not fount by username: " + username);
            throw new UsernameNotFoundException("User not fount by username: " + username);
        } else {
            user.setLastLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            usersRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returning found user by username :" + username);
            return userPrincipal;
        }
    }
}
