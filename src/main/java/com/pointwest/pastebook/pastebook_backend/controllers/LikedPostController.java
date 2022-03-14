package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.services.LikedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class LikedPostController {
  @Autowired private LikedPostService likedPostService;

  @PostMapping("/like/{postId}")
  public ResponseEntity<Object> likePost(
      @PathVariable Long postId, @RequestHeader(value = "Authorization") String stringToken) {
    return ResponseEntity.ok().body(likedPostService.likePost(postId, stringToken));
  }

  @RequestMapping(value = "/like/{postId}", method = RequestMethod.DELETE)
  public ResponseEntity<Object> unLikePost(
      @PathVariable Long postId, @RequestHeader(value = "Authorization") String stringToken) {
    return likedPostService.unlikePost(postId, stringToken);
  }
}