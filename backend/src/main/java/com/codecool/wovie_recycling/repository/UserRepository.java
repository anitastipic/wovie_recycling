package com.codecool.wovie_recycling.repository;

import com.codecool.wovie_recycling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
