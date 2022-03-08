package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // create user
    @RequestMapping(value="/users", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // get users
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        return userService.getUsers();
    }

    // get user
    @RequestMapping(value="/users/{userid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable Long userid) {
        return userService.getUser(userid);
    }

    // update user
    @RequestMapping(value="/users/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long userid) {
        return userService.updateUser(user, userid);
    }

    // search user
    @RequestMapping(value="/api/users/search", method = RequestMethod.GET)
    public ResponseEntity<Object> searchUser(@RequestParam(value="name", defaultValue="") String searchTerm) {
        return userService.searchUser(searchTerm);
    }
}
