package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.exceptions.UserException;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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
            user.setFirstName(body.get("firstname"));
            user.setLastName(body.get("lastname"));
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

            return userService.createUser(user);

        }

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

    // update user details
    @RequestMapping(value="/users/details/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUserDetails(@RequestBody User user, @PathVariable Long userid) {
        return userService.updateUser(user, userid);
    }

    // update user details
    @RequestMapping(value="/users/security/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePassword(@RequestBody User user, @PathVariable Long userid) {
        return userService.updateUser(user, userid);
    }

    // search user
    @RequestMapping(value="/api/users/search", method = RequestMethod.GET)
    public ResponseEntity<Object> searchUser(@RequestParam(value="name", defaultValue="") String searchTerm) {
        return userService.searchUser(searchTerm);
    }
}
