package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // create user
    public ResponseEntity createUser(User user) {
        // adding datetime_created
        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setBirthday(user.getBirthday());
        newUser.setGender(user.getGender());
        newUser.setMobileNumber(user.getMobileNumber());
        newUser.setAboutMe(user.getAboutMe());
        newUser.setProfilePic(user.getProfilePic());
        newUser.setProfileUrl(user.getProfileUrl());

        // getting 'Date' object and converting it to string
        LocalDateTime dateObject = LocalDateTime.now();
        DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String formattedDate = dateObject.format(formatDateObj);

        newUser.setDatetimeCreated(formattedDate);

        userRepository.save(newUser);
        return new ResponseEntity("User created successfully!", HttpStatus.CREATED);
    }

    // get users
    public ResponseEntity getUsers() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    // get user
    public ResponseEntity getUser(Long id) {
        User user = userRepository.findById(id).get();
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // update user
    public ResponseEntity updateUser(User user, Long id) {
        User userToUpdate = userRepository.findById(id).get();

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setBirthday(user.getBirthday());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setMobileNumber(user.getMobileNumber());
        userToUpdate.setOnline(user.isOnline());

        userRepository.save(userToUpdate);

        return new ResponseEntity("User updated successfully!", HttpStatus.OK);
    }

    // search user
    public ResponseEntity searchUser(String searchTerm) {
        ArrayList<User> searchedUsers = new ArrayList<>();
        ArrayList<String> searchedUsersUrl = new ArrayList<>();
        ArrayList<User> searchedUsersAlphabetical = new ArrayList<>();

        if (searchTerm.isBlank()) {
            return new ResponseEntity("No users found", HttpStatus.OK);
        } else {
            for (User existingUser : userRepository.findAll()) {
                // getting user by first name or last name
                if (existingUser.getFirstName().toLowerCase().contains(searchTerm.toLowerCase()) || existingUser.getLastName().toLowerCase().contains(searchTerm.toLowerCase())) {
                    searchedUsers.add(existingUser);
                    searchedUsersUrl.add(existingUser.getFirstName() + existingUser.getLastName() + existingUser.getId());
                }
            }

            // To arrange the users alphabetically, we first need to sort the 'searchedUsersUrl' array list
            // This is an array list arranged alphabetically
            Collections.sort(searchedUsersUrl);

            // Then, we need to associate the array list above with its corresponding object, where that object will be stored in an array list that we will display
            for (String stringUrl : searchedUsersUrl) {
                for (User user : searchedUsers) {
                    if (stringUrl.toLowerCase().equals(user.getFirstName().toLowerCase() + user.getLastName().toLowerCase() + user.getId())) {
                        searchedUsersAlphabetical.add(user);
                    }
                }
            }

            if (searchedUsers.size() == 0) {
                return new ResponseEntity("No users found", HttpStatus.OK);
            }
            return new ResponseEntity(searchedUsersAlphabetical, HttpStatus.OK);
        }
    }
}
