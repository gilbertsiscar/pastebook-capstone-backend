package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import com.pointwest.pastebook.pastebook_backend.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LikedPostService {
    boolean likePost(Long postId, String token);

    Integer getLikes(Long postId);

    ResponseEntity unlikePost(Long postId, String token);

    //Iterable<Post> getTaggedPosts(String stringToken);
    //Iterable<Post> getAllPostRelatedToUser(Long userId, String stringToken);

}