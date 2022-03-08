package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;

@Entity
@Table(name="liked_photos")
public class LikedPhoto {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "liked_photos_seq")
    @SequenceGenerator(name = "liked_photos_seq", sequenceName = "sequence_liked_photos", allocationSize = 1)
    private Long id;

    @Column
    private String datetimeCreated;

    // This refers to the user who 'likes' the photo
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    // Constructors
    public LikedPhoto() {
    }

    public LikedPhoto(String datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public LikedPhoto(String datetimeCreated, User user, Photo photo) {
        this.datetimeCreated = datetimeCreated;
        this.user = user;
        this.photo = photo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(String datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }


}
