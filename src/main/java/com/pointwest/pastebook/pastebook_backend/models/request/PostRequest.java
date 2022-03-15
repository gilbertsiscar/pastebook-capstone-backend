package com.pointwest.pastebook.pastebook_backend.models.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class PostRequest implements Serializable {
  private MultipartFile img;
  private String content;
  private String date_created;

  public MultipartFile getImg() {
    return img;
  }

  public String getContent() {
    return content;
  }

  public String getDate_created() {
    return date_created;
  }
}