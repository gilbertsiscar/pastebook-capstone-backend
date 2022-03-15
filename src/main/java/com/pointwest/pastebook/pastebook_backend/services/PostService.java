package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface PostService {
    Post createPost(Post post, String token);

    Post getPostById(Long id);

    Iterable<Post> getPostsFromUser(String stringToken);

    Iterable<Post> getAllPost();

    //
    // creating a post
//    ResponseEntity createPost(Post post, String token, ArrayList<String> taggedIds);
//
//    Iterable<Post> getPostsFromUser(String stringToken);
//    Iterable<Post> getTaggedPosts(String stringToken);
//    Iterable<Post> getAllPostRelatedToUser(Long userId, String stringToken);
//
//
//
//    // getting all posts of a particular user
//    //ResponseEntity getPostsFromUser(Long visitorId, Long ownerId);
//
//    //ResponseEntity getPostsRelatedToUser(String token);
// //Post createPost(Post post, String token);
//
//  Post getPostById(Long id);
//
//  //Iterable<Post> getPostsFromUser(String stringToken);
//
//  Iterable<Post> getAllPost();
}

