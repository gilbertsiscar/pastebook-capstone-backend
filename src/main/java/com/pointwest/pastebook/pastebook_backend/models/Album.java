package com.pointwest.pastebook.pastebook_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="albums")
public class Album {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albums_seq")
    @SequenceGenerator(name = "albums_seq", sequenceName = "sequence_albums", allocationSize = 1)
    private Long id;

    @Column
    private String albumName;

    @Column
    private String datetimeCreated;

    @OneToMany(mappedBy = "album", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Photo> photos;

    // ManyToOne relationship between Album Model and User Model
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    // Constructors
    public Album() {
    }

    public Album(String albumName, String datetimeCreated) {
        this.albumName = albumName;
        this.datetimeCreated = datetimeCreated;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(String datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }


}
