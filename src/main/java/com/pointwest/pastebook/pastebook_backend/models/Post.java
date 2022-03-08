package com.pointwest.pastebook.pastebook_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @SequenceGenerator(name = "posts_seq", sequenceName = "sequence_posts", allocationSize = 1)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String datetimeCreated;

    @OneToOne
    @JoinColumn(name = "sender_user_id", referencedColumnName = "id")
    private User senderUser;

    // NOTE: this particular block of code is important
    @ManyToOne
    @JoinColumn(name = "receiver_user_id", referencedColumnName = "id")
    private User receiverUser;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<Comment> comments;

    // Constructors
    public Post() {
    }

    public Post(String title, String content, User senderUser, User receiverUser) {
        this.title = title;
        this.content = content;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
    }

    public Post(String title, String content, String datetimeCreated, User senderUser, User receiverUser) {
        this.title = title;
        this.content = content;
        this.datetimeCreated = datetimeCreated;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
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

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
