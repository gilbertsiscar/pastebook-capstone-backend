package com.pointwest.pastebook.pastebook_backend.models.dto;

import java.io.Serializable;
import java.util.List;

public class PostDto implements Serializable {
  private String content;
  private List<Long> tagged;

  public String getContent() {
    return content;
  }

  public List<Long> getTagged() {
    return tagged;
  }
}