package com.mybizexchange.service;

import com.mybizexchange.model.User;
import com.mybizexchange.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repo, BCryptPasswordEncoder encoder){
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(String username, String password, String email, String displayName){
        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(encoder.encode(password));
        u.setEmail(email);
        u.setDisplayName(displayName);
        u.setRole("USER");
        return repo.save(u);
    }

    public Optional<User> findByUsername(String username){
        return repo.findByUsername(username);
    }
}
