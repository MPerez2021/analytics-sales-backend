package com.project.ecommerceBi.security.services;

import com.project.ecommerceBi.security.entities.User;
import com.project.ecommerceBi.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUserNameOrEmail(String nombreOrEmail) {
        return userRepository.findByUserNameOrEmail(nombreOrEmail, nombreOrEmail);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
