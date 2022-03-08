package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;

@Entity
@Table(name="photos")
public class Photo {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photos_seq")
    @SequenceGenerator(name = "photos_seq", sequenceName = "sequence_photos", allocationSize = 1)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String photoFileName;

    @Column
    private String datetimeCreated;

    @ManyToOne
    @JoinColumn(name="album_id", nullable = false)
    private Album album;

    // Constructors
    public Photo() {
    }

    public Photo(String title, String content, String photoFileName, String datetimeCreated) {
        this.title = title;
        this.content = content;
        this.photoFileName = photoFileName;
        this.datetimeCreated = datetimeCreated;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(String datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

}
