package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.config.JwtToken;
import com.pointwest.pastebook.pastebook_backend.models.JwtRequest;
import com.pointwest.pastebook.pastebook_backend.models.JwtResponse;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.services.JwtUserDetailService;
import com.pointwest.pastebook.pastebook_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailService userDetailsService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/api/users/login"}, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        HashMap<String, String> response = new HashMap<>();
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtToken.generateToken(userDetails);

        response.put("result", "successful");
        response.put("id", jwtToken.getIdFromToken(token));
        response.put("email", userDetails.getUsername());
        response.put("token", token);

        //return ResponseEntity.ok(new JwtResponse(token));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // update user credentials
    @RequestMapping(value="/api/users/security/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCredentials(@RequestBody Map<String, String> body
            , @PathVariable Long userid
            , @RequestHeader (value = "Authorization") String stringToken) throws Exception {

        String authenticatedEmail = jwtToken.getUsernameFromToken(stringToken);
        String password = body.get("password");
        System.out.println(authenticatedEmail);
        System.out.println(password);
        authenticate(authenticatedEmail,password);

        User userCredentials = new User();
        userCredentials.setEmail(body.get("newemail"));
        userCredentials.setPassword("newpassword");
        return userService.updateUserCredentials(userCredentials, userid, stringToken);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
