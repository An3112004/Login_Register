package com.example.login_register.repository;

import com.example.login_register.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findUsersById(Long id);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByProviderAndProviderId(String provider, String id);
}
