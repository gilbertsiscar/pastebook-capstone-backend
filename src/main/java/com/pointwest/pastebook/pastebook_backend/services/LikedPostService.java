package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface LikedPostService {
    ResponseEntity likePost(Long postId, String token);
    ResponseEntity unlikePost(Long postId, String token);

    Iterable<Post> getLikesFromAPost(Long postId, String stringToken);
    //Iterable<Post> getTaggedPosts(String stringToken);
    //Iterable<Post> getAllPostRelatedToUser(Long userId, String stringToken);

}
