package com.fecd.auth.models.dao;

import com.fecd.auth.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String email);
}
