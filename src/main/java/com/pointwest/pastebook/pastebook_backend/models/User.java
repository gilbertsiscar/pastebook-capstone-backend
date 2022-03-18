package com.pointwest.pastebook.pastebook_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@DynamicUpdate
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

    @OneToOne(cascade = CascadeType.PERSIST)
    private Image image;

    @Column
    private String aboutMe;

    @Column
    private String verificationCode;

    private boolean enabled;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Tag> tags = new ArrayList<>();

//    public List<Tag> getTags() {
//      return tags;
//    }
//
//    public void setTags(List<Tag> tags) {
//      this.tags = tags;
//    }


  //    @ManyToMany(mappedBy = "tagged")
//    @JsonIgnore
//    private Set<Post> tagged = new HashSet<>();
//
//
//    public Set<Post> getTagged() {
//        return tagged;
//    }
//
//    public void setTagged(Set<Post> tagged) {
//        this.tagged = tagged;
//    }
//
    @Column
    private String profileUrl;


    // NOTE: this particular block of code is important
    // OneToMany relationship between User Model and Post Model
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<LikedPost> likedPost = new ArrayList<>();

    public List<LikedPost> getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(List<LikedPost> likedPost) {
        this.likedPost = likedPost;
    }

    //Tagged posts
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="post_id", nullable=false)
//    private Post post;


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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

//    public String getProfilePic() {
//        return profilePic;
//    }
//
//    public void setProfilePic(String profilePic) {
//        this.profilePic = profilePic;
//    }

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