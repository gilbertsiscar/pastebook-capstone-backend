package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.config.JwtToken;
import com.pointwest.pastebook.pastebook_backend.models.JwtRequest;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.models.dto.UserDto;
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
  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtToken jwtToken;

  @Autowired private JwtUserDetailService userDetailsService;

  @Autowired private UserService userService;

  @RequestMapping(
      value = {"/api/users/login"},
      method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
      throws Exception {
    HashMap<String, String> response = new HashMap<>();

    if (authenticationRequest.getMobile() != null) {
      User usermobile = userService.findByMobile(authenticationRequest.getMobile()).get();
      System.out.println(usermobile.getPassword());
      authenticationRequest.setEmail(usermobile.getEmail());
      // authenticationRequest.setPassword(usermobile.getPassword());
    } else {
      authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
    }
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

    final String token = jwtToken.generateToken(userDetails);

    String firstName = userService.findByEmail(userDetails.getUsername()).get().getFirstName();
    String lastname = userService.findByEmail(userDetails.getUsername()).get().getLastName();
    Long idNumber = userService.findByEmail(userDetails.getUsername()).get().getId();
    String profileUrl = userService.findByEmail(userDetails.getUsername()).get().getProfileUrl();

    response.put("result", "successful");
    response.put("id", jwtToken.getIdFromToken(token));
    response.put("email", userDetails.getUsername());
    response.put("name", firstName + " " + lastname);
    response.put("token", token);
    response.put("idNumber", Long.toString(idNumber));
    response.put("profileUrl", profileUrl);

    // return ResponseEntity.ok(new JwtResponse(token));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  // update user credentials
  @PutMapping("/api/users/security/{userId}")
  public ResponseEntity<Object> updateCredentials(
      @RequestBody UserDto userDto, @PathVariable Long userId) {
    User user = new User();
    user.setEmail(userDto.getEmail());
    user.setMobileNumber(userDto.getMobileNumber());
    user.setPassword(userDto.getPassword());
    User updatedUser = userService.updateUserCredentials(user, userId);

    final UserDetails userDetails = userDetailsService.loadUserByUsername(updatedUser.getEmail());

    final String token = jwtToken.generateToken(userDetails);

    String firstName = userService.findByEmail(userDetails.getUsername()).get().getFirstName();
    String lastname = userService.findByEmail(userDetails.getUsername()).get().getLastName();
    Long idNumber = userService.findByEmail(userDetails.getUsername()).get().getId();
    String profileUrl = userService.findByEmail(userDetails.getUsername()).get().getProfileUrl();

    Map<String, String> response = new HashMap<>();
    response.put("result", "successful");
    response.put("id", jwtToken.getIdFromToken(token));
    response.put("email", userDetails.getUsername());
    response.put("name", firstName + " " + lastname);
    response.put("token", token);
    response.put("idNumber", Long.toString(idNumber));
    response.put("profileUrl", profileUrl);

    return ResponseEntity.ok().body(response);
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}