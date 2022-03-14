package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface UserService {

    // create user
    ResponseEntity createUser(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;

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
}
