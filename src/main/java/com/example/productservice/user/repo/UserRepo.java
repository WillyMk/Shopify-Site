package com.example.productservice.user.repo;

import com.example.productservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
    Optional<User> findByEmail(String email);
}
