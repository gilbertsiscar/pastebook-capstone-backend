package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;

@Entity
@Table(name="friend_requests")
public class FriendRequest {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_requests_seq")
    @SequenceGenerator(name = "friend_requests_seq", sequenceName = "sequence_friend_requests", allocationSize = 1)
    private Long id;

    @Column
    private boolean isActive;

    @Column
    private String datetimeCreated;

    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiver;

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    @Column
    private boolean isSeen = false;

    // Constructors
    public FriendRequest() {
    }

    public FriendRequest(User sender, User receiver, boolean isSeen) {
        this.sender = sender;
        this.receiver = receiver;
        this.isSeen = isSeen;
    }

    public FriendRequest(String datetimeCreated, User sender, User receiver) {
        this.datetimeCreated = datetimeCreated;
        this.sender = sender;
        this.receiver = receiver;
    }

    public FriendRequest(boolean isActive, String datetimeCreated, User sender, User receiver) {
        this.isActive = isActive;
        this.datetimeCreated = datetimeCreated;
        this.sender = sender;
        this.receiver = receiver;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
