package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Photo;
import org.springframework.http.ResponseEntity;

public interface PhotoService {

    // create photo
    ResponseEntity createPhoto(Photo photo);

    // get photos
    ResponseEntity getPhotos();

    // add photo
    ResponseEntity addPhoto(Photo photo, Long albumId);

    // get photos from a particular album
    ResponseEntity getPhotosFromAlbum(Long albumId);

}
