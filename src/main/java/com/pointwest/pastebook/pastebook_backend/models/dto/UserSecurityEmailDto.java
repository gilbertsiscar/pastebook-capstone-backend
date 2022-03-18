package com.pointwest.pastebook.pastebook_backend.models.dto;

import java.io.Serializable;

public class UserSecurityEmailDto implements Serializable {
  private String currentEmail;
  private String newEmail;
  private String password;

  public String getCurrentEmail() {
    return currentEmail;
  }

  public String getNewEmail() {
    return newEmail;
  }

  public String getPassword() {
    return password;
  }
}