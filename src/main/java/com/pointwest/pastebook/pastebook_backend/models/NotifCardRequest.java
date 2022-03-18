package com.pointwest.pastebook.pastebook_backend.models;

import java.sql.Timestamp;

public class NotifCardRequest {

    public Long id;
    public String user;
    public String action;
    public Long post_id;
    public boolean isRead;
    public Timestamp datetimeCreated;

    public NotifCardRequest(Long id, String user, String action, Long post_id, boolean isRead, Timestamp datetimeCreated) {
        this.id = id;
        this.user = user;
        this.action = action;
        this.post_id = post_id;
        this.isRead = isRead;
        this.datetimeCreated = datetimeCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Timestamp getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(Timestamp datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

}
