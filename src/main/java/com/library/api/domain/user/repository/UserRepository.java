package com.library.api.domain.user.repository;

import com.library.api.domain.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    UserDetails findByEmail(String username);
}
