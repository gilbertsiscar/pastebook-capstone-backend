package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
public class Albums {
  @Column(name = "date_created", nullable = false)
  private final LocalDate dateCreated = LocalDate.now();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "album_name", nullable = false)
  private String albumName;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private User user;

  public Albums() {}

  public Albums(String albumName) {
    this.albumName = albumName;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public String getAlbumName() {
    return albumName;
  }

  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getId() {
    return id;
  }
}