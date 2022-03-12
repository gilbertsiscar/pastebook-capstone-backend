package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.repositories.CommentRepository;
import com.pointwest.pastebook.pastebook_backend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value="/comment/{postId}", method = RequestMethod.GET)
    public ResponseEntity<Object> likePost(
            @PathVariable Long postId,
            @RequestBody Map<String, String> body,
            @RequestHeader (value = "Authorization") String stringToken)
    {

        return commentService.commentPost(postId,body.get("comment"), stringToken);
    }
}
