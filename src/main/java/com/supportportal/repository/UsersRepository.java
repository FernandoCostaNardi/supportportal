package com.supportportal.repository;

import com.supportportal.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findUsersByUsername(String username);

    Users findUsersByEmail(String email);
}
