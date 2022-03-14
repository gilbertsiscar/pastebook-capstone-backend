package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import com.pointwest.pastebook.pastebook_backend.models.PostRequest;
import com.pointwest.pastebook.pastebook_backend.services.PostService;
import com.pointwest.pastebook.pastebook_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/posts")
public class PostController {

  @Autowired private PostService postService;

  // POST /api/posts
  @PostMapping
  public ResponseEntity<Post> createPost(
      @RequestBody PostRequest postRequest,
      @RequestHeader(value = "Authorization") String stringToken) {
    Post post = new Post();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    post.setDatetimeCreated(dtf.format(now));
    post.setContent(postRequest.getContent());

    return ResponseEntity.ok().body(postService.createPost(post, stringToken));
  }

  // GET /api/posts/{id}
  @GetMapping("/{postId}")
  public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
    return ResponseEntity.ok().body(postService.getPostById(postId));
  }

  // GET /api/posts
  @GetMapping
  public ResponseEntity<Iterable<Post>> getAllPostFromUser(
      @RequestHeader(value = "Authorization") String token) {
    return ResponseEntity.ok().body(postService.getPostsFromUser(token));
  }
}