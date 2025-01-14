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

    @RequestMapping(value="/comment/{postId}", method = RequestMethod.POST)
    public ResponseEntity<Object> addCommentToPost(
            @PathVariable Long postId,
            @RequestBody Map<String, String> body,
            @RequestHeader (value = "Authorization") String stringToken)
    {

        return commentService.commentPost(postId,body.get("comment"), stringToken);
    }

    @RequestMapping(value="/comment/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeCommentTOPost(
            @PathVariable Long commentId,
            @RequestHeader (value = "Authorization") String stringToken)
    {
        return commentService.removeComment(commentId, stringToken);
    }

    @RequestMapping(value="/comment/{commentId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editCommentFromPost(
            @PathVariable Long commentId,
            @RequestBody Map<String, String> body,
            @RequestHeader (value = "Authorization") String stringToken)
    {

        return commentService.editComment(commentId,body.get("comment"), stringToken);
    }



}
