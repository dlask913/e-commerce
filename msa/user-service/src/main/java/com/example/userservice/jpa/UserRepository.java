package com.example.userservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserId(String userId);
    User findByEmail(String username);

}
