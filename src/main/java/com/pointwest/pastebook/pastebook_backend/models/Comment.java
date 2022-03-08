package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq")
    @SequenceGenerator(name = "comments_seq", sequenceName = "sequence_comments", allocationSize = 1)
    private Long id;

    @Column
    private String content;

    @Column
    private String datetimeCreated;

    // ManyToOne relationship between Comment Model and User Model
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    // ManyToOne relationship between Comment Model and Post Model
    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    // Constructors
    public Comment() {
    }

    public Comment(String content, String datetimeCreated) {
        this.content = content;
        this.datetimeCreated = datetimeCreated;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}
