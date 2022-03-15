package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  private String type;

  @Column(name = "picByte", length = 200000)
  private byte[] picByte;

  public Image(String name, String type, byte[] picByte) {
    this.name = name;
    this.type = type;
    this.picByte = picByte;
  }

  public Image() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getPicByte() {
    return picByte;
  }

  public void setPicByte(byte[] picByte) {
    this.picByte = picByte;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}