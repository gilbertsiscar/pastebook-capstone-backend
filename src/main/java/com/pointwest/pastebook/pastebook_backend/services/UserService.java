package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    // create user
    ResponseEntity createUser(User user);

    // update user
    ResponseEntity updateUserCredentials(User user, Long id, String token);

    ResponseEntity updateUserPersonalDetails(User user, Long id, String token);

    ResponseEntity updateAboutMe(String aboutMe, Long id, String token);


    // For getting
    User getUser(Long id, String token);

    // search user
    ResponseEntity searchUser(String searchTerm);

    Optional<User> findByEmail(String email);
}
