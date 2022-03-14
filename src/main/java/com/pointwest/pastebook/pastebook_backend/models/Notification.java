package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;

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
    private String status;

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column
    private String datetimeCreated;

    @OneToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiver;

    // Constructors
    public Notification() {
    }

    public Notification(String content, String status, String datetimeCreated) {
        this.content = content;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
