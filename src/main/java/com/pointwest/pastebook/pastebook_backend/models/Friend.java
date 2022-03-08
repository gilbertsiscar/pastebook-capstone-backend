package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;

@Entity
@Table(name="friends")
public class Friend {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friends_seq")
    @SequenceGenerator(name = "friends_seq", sequenceName = "sequence_friends", allocationSize = 1)
    private Long id;

    @Column
    private String datetimeCreated;

    @OneToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "id")
    private User requester;

    @OneToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private User recipient;

    // Constructors
    public Friend() {
    }

    // This is an important constructor for the FriendService
    public Friend(User requester, User recipient) {
        this.requester = requester;
        this.recipient = recipient;
    }

    // This is an important constructor for the FriendService
    public Friend(String datetimeCreated, User requester, User recipient) {
        this.datetimeCreated = datetimeCreated;
        this.requester = requester;
        this.recipient = recipient;
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

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }


}
