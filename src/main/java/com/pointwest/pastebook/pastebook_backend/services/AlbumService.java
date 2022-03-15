package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Album;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface AlbumService {

    // create album
    ResponseEntity createAlbum(HashMap<String, Object> albumMap);


    // rename album
    ResponseEntity renameAlbum(HashMap<String, Object> albumMap, Long albumId);

    // delete album
    ResponseEntity deleteAlbum(Long albumId);

//    // add album
//    ResponseEntity addAlbum(Album album, Long userId);

    // get photos from a particular album
    ResponseEntity getAlbumsFromUser(Long userId);

}
