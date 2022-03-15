package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.models.Album;
import com.pointwest.pastebook.pastebook_backend.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    // create album
    @RequestMapping(value="/api/albums", method = RequestMethod.POST)
    public ResponseEntity<Object> createAlbum(@RequestBody HashMap<String, Object> albumMap) {
        return albumService.createAlbum(albumMap);
    }

    // rename album
    @RequestMapping(value="/api/albums/{albumId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> renameAlbum(@RequestBody HashMap<String, Object> albumMap, @PathVariable Long albumId) {
        return albumService.renameAlbum(albumMap, albumId);
    }

    // delete album
    @RequestMapping(value="/api/albums/{albumId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAlbum(@PathVariable Long albumId) {
        return albumService.deleteAlbum(albumId);
    }

//    // add album
//    @RequestMapping(value="/albums/{userId}", method = RequestMethod.POST)
//    public ResponseEntity<Object> addAlbum(@RequestBody Album album, @PathVariable Long userId) {
//        return albumService.addAlbum(album, userId);
//    }

    // get albums from a particular user
    @RequestMapping(value="/api/albums/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAlbumsFromUser(@PathVariable Long userId) {
        return albumService.getAlbumsFromUser(userId);
    }

}
