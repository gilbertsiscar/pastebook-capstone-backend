package com.pointwest.pastebook.pastebook_backend.models;

import javax.persistence.*;

@Entity
@Table(name="blocks")
public class Block {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blocks_seq")
    @SequenceGenerator(name = "blocks_seq", sequenceName = "sequence_blocks", allocationSize = 1)
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
    public Block() {
    }

    public Block(String datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
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
