package com.example.login_register.service;

import com.example.login_register.domain.User;
import com.example.login_register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(User user) {
        if(this.userRepository.existsByUsername(user.getUsername())){
            throw  new RuntimeException("Ten da ton tai");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return this.userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {

        Optional<User> curUserOpt = userRepository.findUsersById(userId);

        if (curUserOpt.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        User curUser = curUserOpt.get();

        // Cập nhật các field cần thiết
        curUser.setUsername(user.getUsername());
        curUser.setEmail(user.getEmail());
        curUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(curUser);
    }


}
