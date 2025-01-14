package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.exceptions.UserException;
import com.pointwest.pastebook.pastebook_backend.models.JwtRequest;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public ResponseEntity<Object> testConnection(){
        return  new ResponseEntity<Object>("Connection alright!", HttpStatus.OK);
    }
    // create user
    @RequestMapping(value="/users/register", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody Map<String, String> body)  throws UserException {
        // do checker for email
        System.out.println("test");
        if(!userService.findByEmail(body.get("email")).isEmpty()){
            //throw new UserException("Username already exists");
            return new ResponseEntity<Object>("Email already exists", HttpStatus.IM_USED);
        }else{
            String password = body.get("password");
            String encodedPassword = new BCryptPasswordEncoder().encode(password);

            User user = new User();
            user.setFirstName(body.get("firstName"));
            user.setLastName(body.get("lastName"));
            user.setBirthday(body.get("birthday"));
            user.setEmail(body.get("email"));
            user.setMobileNumber(body.get("mobilenumber"));
            user.setPassword(encodedPassword);
            // default values
            user.setOnline(false);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            user.setDatetimeCreated(dtf.format(now));

            user.setAboutMe("");
            user.setProfileUrl("");
            user.setProfilePic("");

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/register").toUriString());

            return ResponseEntity.created(uri).body(user);

        }

    }

    @RequestMapping(value="/users/details/{userid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAccountDetails(
            @PathVariable Long userid
            ,@RequestHeader (value = "Authorization") String stringToken)
    {
        return new ResponseEntity<>(userService.getUser(userid,stringToken), HttpStatus.OK);
    }

    //
    @RequestMapping(value="/users/profile/{profileUrl}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProfile(
            @PathVariable String profileUrl
            ,@RequestHeader (value = "Authorization") String stringToken)
    {
        return userService.getProfile(profileUrl,stringToken);
    }

    // update user details
    @RequestMapping(value="/users/details/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUserPersonalDetails(@RequestBody Map<String, String> body
            ,@PathVariable Long userid
            ,@RequestHeader (value = "Authorization") String stringToken) {

        User userDetails = new User();
        userDetails.setFirstName(body.get("firstname"));
        userDetails.setLastName(body.get("lastname"));
        userDetails.setBirthday(body.get("birthday"));
        userDetails.setGender(body.get("gender"));

        return userService.updateUserPersonalDetails(userDetails, userid, stringToken);
    }


// Moved to AuthContoller
//        // update user credentials
//    @RequestMapping(value="/users/security/{userid}", method = RequestMethod.PUT)
//    public ResponseEntity<Object> updatePassword(@RequestBody Map<String, String> body
//            , @PathVariable Long userid
//            , @RequestHeader (value = "Authorization") String stringToken) {
//
//
//        User userCredentials = new User();
//        userCredentials.setEmail(body.get("newpassword"));
//        userCredentials.setPassword("newemail");
//        userService.updateUserCredentials(userCredentials, userid, stringToken);
//        return null;
//    }

    // search user
    @RequestMapping(value="/users/search/{searchTerm}", method = RequestMethod.GET)
    public ResponseEntity<Object> searchUser(
            @PathVariable String searchTerm
            ,@RequestHeader (value = "Authorization") String stringToken) {
        return userService.searchUser(searchTerm, stringToken);
    }

    @RequestMapping(value="/users/aboutme/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> searchUser(@RequestBody Map<String, String> body
            ,@PathVariable Long userId
            ,@RequestHeader (value = "Authorization") String stringToken){


        return userService.updateAboutMe(body.get("aboutme"), userId,stringToken);
    }

    //Not needed
    // get users
//    @RequestMapping(value="/users", method = RequestMethod.GET)
//    public ResponseEntity<Object> getUsers() {
//        return userService.getUsers();
//    }
//
//    // get user
//    @RequestMapping(value="/users/{userid}", method = RequestMethod.GET)
//    public ResponseEntity<Object> getUser(@PathVariable Long userid) {
//        return userService.getUser(userid);
//    }


}