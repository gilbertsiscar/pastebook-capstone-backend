package com.pointwest.pastebook.pastebook_backend.models.dto;

import java.io.Serializable;

public class UserSecurityPasswordDto implements Serializable {
  private String currentPassword;
  private String newPassword;

  public String getCurrentPassword() {
    return currentPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}