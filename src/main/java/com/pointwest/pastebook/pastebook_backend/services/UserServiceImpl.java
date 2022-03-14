package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.config.JwtToken;
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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtToken jwtToken;

    // create user
    public ResponseEntity createUser(User user) {
        userRepository.save(user);
        user.setProfileUrl(user.getFirstName()+user.getLastName()+user.getId());
        userRepository.save(user);
        return new ResponseEntity("User created successfully!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateUserCredentials(User user, Long id, String token) {
        User userForUpdating = userRepository.findById(id).get();

        if(userForUpdating != null) {
            String authenticatedEmail = jwtToken.getUsernameFromToken(token);
            if (authenticatedEmail.equalsIgnoreCase(userForUpdating.getEmail())) {
                // Add email checker if unique
                userForUpdating.setEmail(user.getEmail());
                userForUpdating.setPassword(user.getPassword());
                userRepository.save(userForUpdating);
                return new ResponseEntity("User details updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity("You are not authorized to edit this profile", HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity("Profile not found", HttpStatus.NOT_FOUND);
        }
    }

    // get users
    public ResponseEntity getUsers() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    // get user
    public User getUser(Long id, String token) {
        // check if you own this account
        return userRepository.findById(id).get();
        // check if its your friend

        // deny

    }

    @Override
    public ResponseEntity getProfile(String profileUrl, String token) {
        //token checker
        User user= userRepository.getUserProfileByUrl(profileUrl);
        if(user != null)
            return new ResponseEntity(user, HttpStatus.OK);
        else
            return new ResponseEntity("User not found!", HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity updateUserPersonalDetails(User user, Long id, String token) {
        User userForUpdating = userRepository.findById(id).get();

        String authenticatedEmail = jwtToken.getUsernameFromToken(token);
        if(authenticatedEmail.equalsIgnoreCase(userForUpdating.getEmail()))
        {
            userForUpdating.setFirstName(user.getFirstName());
            userForUpdating.setLastName(user.getLastName());
            userForUpdating.setGender(user.getGender());
            userForUpdating.setBirthday(user.getBirthday());
            userRepository.save(userForUpdating);

            return new ResponseEntity("User details updated successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity("You are not authorized to edit this profile", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity updateAboutMe(String aboutMe, Long id, String token) {
        System.out.println("Id token is");
        System.out.println(jwtToken.getIdFromToken(token));
        Long authenticatedId = Long.parseLong(jwtToken.getIdFromToken(token));

        if(authenticatedId == id){
            User user = userRepository.findById(authenticatedId).get();
            //check if empty later
            user.setAboutMe(aboutMe);
            userRepository.save(user);
            return new ResponseEntity("Aboue me Updated", HttpStatus.OK);
        }else{
            return new ResponseEntity("You are not authorized to edit this aboutMe", HttpStatus.UNAUTHORIZED);
        }
    }

    // search user
    public ResponseEntity searchUser(String searchTerm, String token) {
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

    @Override
    public Optional<User> findByEmail(String username) {
        return Optional.ofNullable(userRepository.findByEmail(username));
    }
}
