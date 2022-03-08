package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.models.Album;
import com.pointwest.pastebook.pastebook_backend.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    // create album
    @RequestMapping(value="/albums", method = RequestMethod.POST)
    public ResponseEntity<Object> createAlbum(@RequestBody Album album) {
        return albumService.createAlbum(album);
    }

    // rename album
    @RequestMapping(value="/albums/{albumId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> renameAlbum(@RequestBody Album album, @PathVariable Long albumId) {
        return albumService.renameAlbum(album, albumId);
    }

    // delete album
    @RequestMapping(value="/albums/{albumId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> renameAlbum(@PathVariable Long albumId) {
        return albumService.deleteAlbum(albumId);
    }

    // add album
    @RequestMapping(value="/albums/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Object> addAlbum(@RequestBody Album album, @PathVariable Long userId) {
        return albumService.addAlbum(album, userId);
    }

    // get photos from a particular album
    @RequestMapping(value="/albums/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAlbumsFromUser(@PathVariable Long userId) {
        return albumService.getAlbumsFromUser(userId);
    }

}
