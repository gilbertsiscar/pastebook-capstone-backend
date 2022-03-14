package com.pointwest.pastebook.pastebook_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "sequence_users", allocationSize = 1)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    //JsonIgnore
    private String password;

    @Column
    private String birthday;

    @Column
    private String gender;

    @Column
    private String mobileNumber;

    @Column
    private boolean isOnline = false;

    @Column
    private String datetimeCreated;

    @Column
    private String profilePic;

    @Column
    private String aboutMe;

    @Column
    private String verificationCode;

    private boolean enabled;

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

//    public Set<Post> getTaggedpost() {
//        return taggedpost;
//    }
//
//    public void setTaggedpost(Set<Post> taggedpost) {
//        this.taggedpost = taggedpost;
//    }

    @Column
    private String profileUrl;

    // NOTE: this particular block of code is important
    // OneToMany relationship between User Model and Post Model
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Post> posts;


    //Tagged posts
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="post_id", nullable=false)
//    private Post post;

//    @ManyToMany(mappedBy = "taggedUsers")
//    @JsonIgnore
//    private Set<Post> taggedpost = new HashSet<>();

    // OneToMany relationship between User Model and Album Model
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Album> albums;

    // OneToMany relationship between User Model and Comment Model
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Comment> comments;

    // Constructors
    public User() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(String datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

//    public Set<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(Set<Post> posts) {
//        this.posts = posts;
//    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}