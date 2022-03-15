package com.pointwest.pastebook.pastebook_backend.services;

import com.pointwest.pastebook.pastebook_backend.models.Album;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.repositories.AlbumRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    // create album
    public ResponseEntity createAlbum(HashMap<String, Object> albumMap) {

        String albumName = albumMap.get("albumName").toString();
        String userIdStr = albumMap.get("userId").toString();

        Long userId = Long.parseLong(userIdStr);

        User albumOwner = userRepository.findById(userId).get();
        Album newAlbum = new Album();

        newAlbum.setAlbumName(albumName);
        newAlbum.setUser(albumOwner);

        // getting 'Date' object and converting it to string
        LocalDateTime dateObject = LocalDateTime.now();
        DateTimeFormatter formatDateObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String formattedDate = dateObject.format(formatDateObj);

        newAlbum.setDatetimeCreated(formattedDate);

        albumRepository.save(newAlbum);

        return new ResponseEntity("Album created successfully!", HttpStatus.CREATED);
    }

    // rename album
    public ResponseEntity renameAlbum(HashMap<String, Object> albumMap, Long albumId) {

        String newAlbumName = albumMap.get("albumName").toString();

        Album albumToUpdate = albumRepository.findById(albumId).get();

        albumToUpdate.setAlbumName(newAlbumName);

        albumRepository.save(albumToUpdate);

        return new ResponseEntity("Album renamed successfully!", HttpStatus.OK);
    }

    // delete album
    public ResponseEntity deleteAlbum(Long albumId) {
        albumRepository.deleteById(albumId);
        return new ResponseEntity("Album deleted successfully!", HttpStatus.OK);
    }

    // add album
    public ResponseEntity addAlbum(Album album, Long userId) {

        // associate a user with corresponding id: userId (wala pa kasing tokens)
        User user = userRepository.findById(userId).get();

        // create a new album to be associated with the created user by using the setUser() method
        Album newAlbum = new Album();

        newAlbum.setAlbumName(album.getAlbumName());
        newAlbum.setUser(user);
        albumRepository.save(newAlbum);

        return new ResponseEntity("Album added successfully!", HttpStatus.CREATED);
    }

    // get photos from a particular album
    public ResponseEntity getAlbumsFromUser(Long userId) {
        // create a for-loop and a conditional statement that will be responsible for getting all of the albums given a particular userId

        ArrayList<Album> albumArr = new ArrayList<>();

        for (Album album : albumRepository.findAll()) {
            if (album.getUser().getId() == userId) {
                // get the records that satisfy the condition by storing them in an Array List
                albumArr.add(album);
            }
        }
        return new ResponseEntity(albumArr, HttpStatus.OK);
    }

    public ArrayList<Album> displayGetAlbumsFromUser(Long userId) {
        // first, create an Array List
        ArrayList<Album> albumArr = new ArrayList<>();

        for (Album album : albumRepository.findAll()) {
            if (album.getUser().getId() == userId) {
                albumArr.add(album);
            }
        }
        return albumArr; // this contains all of the albums that satisfy the given userId
    }

}
