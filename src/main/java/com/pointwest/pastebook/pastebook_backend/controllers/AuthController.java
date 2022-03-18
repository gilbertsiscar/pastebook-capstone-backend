package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.config.JwtToken;
import com.pointwest.pastebook.pastebook_backend.models.JwtRequest;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.models.dto.UserDto;
import com.pointwest.pastebook.pastebook_backend.models.dto.UserSecurityEmailDto;
import com.pointwest.pastebook.pastebook_backend.models.dto.UserSecurityPasswordDto;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class AuthController {
  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtToken jwtToken;

  @Autowired private JwtUserDetailService userDetailsService;

  @Autowired private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
      throws Exception {
    HashMap<String, Object> response = new HashMap<>();

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

    User user = userService.findByEmail(userDetails.getUsername()).get();

//    String firstName = userService.findByEmail(userDetails.getUsername()).get().getFirstName();
//    String lastname = userService.findByEmail(userDetails.getUsername()).get().getLastName();
//    Long idNumber = userService.findByEmail(userDetails.getUsername()).get().getId();
//    String profileUrl = userService.findByEmail(userDetails.getUsername()).get().getProfileUrl();

    response.put("result", "successful");
    response.put("id", user.getId());
    response.put("email", user.getEmail());
    response.put("name", user.getFirstName() + " " + user.getLastName());
    response.put("token", token);
    response.put("idNumber", user.getId());
    response.put("profileUrl", user.getProfileUrl());
    response.put("profilePic", user.getImage());

    // return ResponseEntity.ok(new JwtResponse(token));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/security/email/{userId}")
  public ResponseEntity<Map<String,String>> updateUserSecurityEmail(
      @RequestBody UserSecurityEmailDto userDto, @PathVariable Long userId) throws Exception {
    authenticate(userDto.getCurrentEmail(), userDto.getPassword());
    User user = new User();
    user.setEmail(userDto.getNewEmail());
    User updatedUser = userService.updateSecurityEmail(user, userId);

    UserDetails userDetails = userDetailsService.loadUserByUsername(updatedUser.getEmail());
    String token = jwtToken.generateToken(userDetails);

    Map<String, String> response = new HashMap<>();
    response.put("result", "successful");
    response.put("id", String.valueOf(updatedUser.getId()));
    response.put("email", updatedUser.getEmail());
    response.put("token", token);
    return ResponseEntity.ok().body(response);
  }

  @PutMapping("/security/password/{userId}")
  public ResponseEntity<User> updateUserSecurityPassword(@RequestBody UserSecurityPasswordDto userDto, @PathVariable Long userId) throws Exception {
    User userDb = userService.getUserById(userId);
    authenticate(userDb.getEmail(), userDto.getCurrentPassword());
    User user = new User();
    String encodedPassword = new BCryptPasswordEncoder().encode(userDto.getNewPassword());
    user.setPassword(encodedPassword);
    return ResponseEntity.ok().body(userService.updateSecurityPassword(user, userId));
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
      System.out.println(e.getMessage());
    }
  }
}