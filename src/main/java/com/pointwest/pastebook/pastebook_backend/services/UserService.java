package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Image;
import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    User updateSecurityEmail(User user, Long id);

    User updateSecurityPassword(User user, Long id);

    User updatePersonalDetails(User user, Long id);

    ResponseEntity updateAboutMe(String aboutMe, Long id, String token);

    ResponseEntity uploadProfilePicture(Image image,String token);

    // For getting
    User getUser(Long id, String token);
    ResponseEntity getProfile(String profileUrl, String token);

    // search user
    ResponseEntity searchUser(String searchTerm, String token);

    Optional<User> findByEmail(String email);
    Optional<User> findByMobile(String mobile);

    // CODES FOR TESTING
    ResponseEntity getUsersTest();

    // get user
    ResponseEntity getUserTest(Long id);

    void setOnlineStatus(Long id);
    void setOfflineStatus(Long id);


}