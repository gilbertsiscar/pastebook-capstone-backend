package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.services.LikedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class LikedPostController {
    @Autowired
    LikedPostService likedPostService;

    @RequestMapping(value="/like/{postId}", method = RequestMethod.GET)
    public ResponseEntity<Object> likePost(
            @PathVariable Long postId,
            @RequestHeader (value = "Authorization") String stringToken)
    {
        return likedPostService.likePost(postId, stringToken);
    }
}
