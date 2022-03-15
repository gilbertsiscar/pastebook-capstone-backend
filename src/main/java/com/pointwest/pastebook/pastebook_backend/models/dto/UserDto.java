package com.pointwest.pastebook.pastebook_backend.models.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String birthday;
  private String gender;
  private String mobileNumber;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getGender() {
    return gender;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }
}