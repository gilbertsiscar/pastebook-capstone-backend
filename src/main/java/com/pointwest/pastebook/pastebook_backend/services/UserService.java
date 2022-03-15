package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    User createUser(User user);

    // update user
    ResponseEntity updateUserCredentials(User user, Long id, String token);

    ResponseEntity updateUserPersonalDetails(User user, Long id, String token);

    ResponseEntity updateAboutMe(String aboutMe, Long id, String token);


    // For getting
    User getUser(Long id, String token);
    ResponseEntity getProfile(String profileUrl, String token);

    // search user
    ResponseEntity searchUser(String searchTerm, String token);

    Optional<User> findByEmail(String email);


    // CODES FOR TESTING
    ResponseEntity getUsersTest();

    // get user
    ResponseEntity getUserTest(Long id);



}