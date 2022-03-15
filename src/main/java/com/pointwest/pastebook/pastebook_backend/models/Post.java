package com.pointwest.pastebook.pastebook_backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "created_at")
    private String datetimeCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<LikedPost> likes;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }

    public List<LikedPost> getLikes() {
        return likes;
    }

    public void setLikes(List<LikedPost> likes) {
        this.likes = likes;
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

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
}
