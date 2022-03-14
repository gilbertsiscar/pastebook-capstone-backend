package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Register the user credentials in the application
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Check if username exists and get the user if it does
        User user = userRepository.findByEmail(username);
        // If no user found throw exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Store user credentials in User object for spring security
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());

    }

}
