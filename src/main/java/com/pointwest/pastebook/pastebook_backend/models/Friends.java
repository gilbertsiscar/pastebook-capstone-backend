package com.pointwest.pastebook.pastebook_backend.models;

import java.util.Date;

public class Friends {
    private int requester_id;
    private int recipient_id;
    private Date date_created;

    public Friends() {
    }

    public Friends(int requester_id, int recipient_id, Date date_created) {
        this.requester_id = requester_id;
        this.recipient_id = recipient_id;
        this.date_created = date_created;
    }

    public int getRequester_id() {
        return requester_id;
    }

    public void setRequester_id(int requester_id) {
        this.requester_id = requester_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
}
