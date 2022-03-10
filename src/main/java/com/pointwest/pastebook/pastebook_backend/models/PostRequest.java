package com.pointwest.pastebook.pastebook_backend.models;

import java.io.Serializable;
import java.util.ArrayList;

public class PostRequest implements Serializable {

    // Properties
    private static final long serialVersionUID = 5926468583005150707L;

    private String content;
    private String date_created;
    private ArrayList<String> taggedIds;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public ArrayList<String> getTaggedIds() {
        return taggedIds;
    }

    public void setTaggedIds(ArrayList<String> taggedIds) {
        this.taggedIds = taggedIds;
    }

    public PostRequest(String content, String date_created, ArrayList<String> taggedIds) {
        this.content = content;
        this.date_created = date_created;
        this.taggedIds = taggedIds;
    }

    // Constructors
    public PostRequest() {
    }


    // Getters and Setters

}
