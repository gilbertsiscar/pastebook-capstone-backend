package com.pointwest.pastebook.pastebook_backend.models;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import com.pointwest.pastebook.pastebook_backend.models.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="notifications")
public class Notification {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notifications_seq")
    @SequenceGenerator(name = "notifications_seq", sequenceName = "sequence_notifications", allocationSize = 1)
    private Long id;

    @Column
    private String content;

    @Column
    private boolean readStatus;

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column
    private Timestamp datetimeCreated;

    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    //For json respond reference

    // Constructors
    public Notification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Notification(Long id, String content, boolean readStatus, Post post, Timestamp datetimeCreated, User sender) {
        this.id = id;
        this.content = content;
        this.readStatus = readStatus;
        this.post = post;
        this.datetimeCreated = datetimeCreated;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Timestamp getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(Timestamp datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
