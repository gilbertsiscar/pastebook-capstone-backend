package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.models.Post;
import com.pointwest.pastebook.pastebook_backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value="/posts/{posterId}/{postedId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createPost(@RequestBody Post post, @PathVariable Long posterId, @PathVariable Long postedId) {
        return postService.createPost(post, posterId, postedId);
    }
}
