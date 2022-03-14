package com.pointwest.pastebook.pastebook_backend.controllers;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pointwest.pastebook.pastebook_backend.models.Post;
import com.pointwest.pastebook.pastebook_backend.models.PostRequest;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.services.PostService;
import com.pointwest.pastebook.pastebook_backend.services.UserService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // create post
    @RequestMapping(value="/posts", method = RequestMethod.POST)
    public ResponseEntity<Object> createPost(@RequestBody PostRequest postRequest
            ,@RequestHeader (value = "Authorization") String stringToken) {
        Post post = new Post();
//        {
//            "title"
//            "content"
//            "date_created"
//            "taggedusers"
//        }
        post.setContent(postRequest.getContent());
        //post.setDatetimeCreated(Date.valueOf(postRequest.getDate_created()));
        //The backend creates the date instead
        ArrayList<String> taggedIds = postRequest.getTaggedIds();
        return postService.createPost(post, stringToken, taggedIds);
    }

    //Get all related post from a user, can be yours or others
    @RequestMapping(value="/posts/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getRelatedPostToUser(
            @PathVariable Long userId,
            @RequestHeader (value = "Authorization") String stringToken) {
//        {
//            "title"
//            "content"
//            "date_created"
//            "receiverId"
//        }
//        post.setContent(postRequest.getContent());
//        //post.setDatetimeCreated(Date.valueOf(postRequest.getDate_created()));
//        ArrayList<String> taggedIds = postRequest.getTaggedIds();
//        return postService.createPost(post, stringToken, taggedIds);
        return new ResponseEntity<>(postService.getAllPostRelatedToUser(userId, stringToken), HttpStatus.OK);
    }

    // getting all posts of a particular user
//    @RequestMapping(value="/posts/{visitorId}/{ownerId}", method = RequestMethod.GET)
//    public ResponseEntity<Object> getPostsFromUser(@PathVariable Long visitorId, @PathVariable Long ownerId) {
//        return postService.getPostsFromUser(visitorId, ownerId);
//    }

}
