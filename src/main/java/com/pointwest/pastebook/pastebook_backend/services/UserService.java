package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    // create user
    ResponseEntity createUser(User user);

    // get users
    ResponseEntity getUsers();

    // get user
    ResponseEntity getUser(Long id);

    // update user
    ResponseEntity updateUser(User user, Long id);

    // search user
    ResponseEntity searchUser(String searchTerm);
}
