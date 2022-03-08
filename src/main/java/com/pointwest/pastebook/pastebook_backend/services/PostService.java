package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    //
    // creating a post
    ResponseEntity createPost(Post post, Long posterId, Long postedId);
}
