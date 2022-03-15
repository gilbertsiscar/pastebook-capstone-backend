package com.pointwest.pastebook.pastebook_backend.models;

import java.io.Serializable;

public class PostRequest implements Serializable {
  private String content;
  private String date_created;

  public String getContent() {
    return content;
  }

  public String getDate_created() {
    return date_created;
  }
}