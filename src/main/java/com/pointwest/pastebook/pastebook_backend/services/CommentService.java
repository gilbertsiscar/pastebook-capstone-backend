package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity commentPost(Long postId, String content, String token);
    ResponseEntity removeComment(Long postId, String token);

    ResponseEntity editComment(Long commentId, String content, String token);

    //Iterable<Post> getCommentsFromPost(Long postId, String stringToken);
}
