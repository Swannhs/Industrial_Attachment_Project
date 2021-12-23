package com.swann.industrialattachment.service;

import com.swann.industrialattachment.repository.UserRepository;
import com.swann.industrialattachment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<?> saveOrUpdate(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            HashMap<String, String> exist = new HashMap<>();
            exist.put("message", "Username is taken");
            return new ResponseEntity<>(exist, HttpStatus.BAD_REQUEST);
        }else {
            user.setPassword(encoder.encode(user.getPassword()));
            try {
                repository.save(user);
                HashMap<String, String> exist = new HashMap<>();
                exist.put("message", "Registration successful");
                return new ResponseEntity<>(exist, HttpStatus.ACCEPTED);
            } catch (Exception e) {
                HashMap<String, String> exist = new HashMap<>();
                exist.put("message", "Failed to register");
                return new ResponseEntity<>(exist, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
