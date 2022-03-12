package com.pointwest.pastebook.pastebook_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="post")
public class Post {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @SequenceGenerator(name = "posts_seq", sequenceName = "sequence_posts", allocationSize = 1)
    private Long id;

    @Column
    private String content;

    @Column
    private Date datetimeCreated;

    @OneToOne
    //@ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    //@JoinColumn(name = "postOwner", referencedColumnName = "id")
    private User postOwner;

//    // NOTE: this particular block of code is important
//    @ManyToOne
//    @JoinColumn(name = "receiver_user_id", referencedColumnName = "id")
//    @JsonIgnore
//    private User receiverUser;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<LikedPost> getLikes() {
        return likes;
    }

    public void setLikes(Set<LikedPost> likes) {
        this.likes = likes;
    }

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post")
    private Set<LikedPost> likes;

    //Users tagged in a post
//    @OneToMany(mappedBy="post")
//    @JsonIgnore
//    private Set<User> taggedUsers;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "TaggedUsers",
            joinColumns = { @JoinColumn(name = "post_id", referencedColumnName = "id" ) },
            inverseJoinColumns = { @JoinColumn(name = "user_id" , referencedColumnName = "id") }
    )
    Set<User> taggedUsers = new HashSet<>();

    public Post() {
    }

    public Post(String content, User postOwner) {
        this.content = content;
        this.postOwner = postOwner;
        //  this.receiverUser = receiverUser;
    }

    public Set<User> getTaggedUsers() {
        return taggedUsers;
    }

    public void setTaggedUsers(Set<User> taggedUsers) {
        this.taggedUsers = taggedUsers;
    }

    public void setPostOwner(User postOwner) {
        this.postOwner = postOwner;
    }

    // Constructors


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

    public Date getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(Date datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

//    public User getReceiverUser() {
//        return receiverUser;
//    }
//
//    public void setReceiverUser(User receiverUser) {
//        this.receiverUser = receiverUser;
//    }

//    public Set<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(Set<Comment> comments) {
//        this.comments = comments;
//    }
}
