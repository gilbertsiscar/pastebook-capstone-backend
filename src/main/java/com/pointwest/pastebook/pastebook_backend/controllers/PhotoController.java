package com.pointwest.pastebook.pastebook_backend.controllers;

import com.pointwest.pastebook.pastebook_backend.models.Album;
import com.pointwest.pastebook.pastebook_backend.models.Photo;
import com.pointwest.pastebook.pastebook_backend.models.User;
import com.pointwest.pastebook.pastebook_backend.repositories.AlbumRepository;
import com.pointwest.pastebook.pastebook_backend.repositories.PhotoRepository;
import com.pointwest.pastebook.pastebook_backend.services.PhotoService;
import com.pointwest.pastebook.pastebook_backend.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    // additional autowired interfaces
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private AlbumRepository albumRepository;

    // create photo
    @RequestMapping(value="/photos", method = RequestMethod.POST)
    public ResponseEntity<Object> createPhoto(@RequestBody Photo photo) {
        return photoService.createPhoto(photo);
    }

    // get photos
    @RequestMapping(value="/photos", method = RequestMethod.GET)
    public ResponseEntity<Object> getPhotos() {
        return photoService.getPhotos();
    }

    // add photo
    @RequestMapping(value="/photos/{albumId}", method = RequestMethod.POST)
    public ResponseEntity<Object> addPhoto(@RequestBody Photo photo, @PathVariable Long albumId) {
        return photoService.addPhoto(photo, albumId);
    }

    // get photos from a particular album
    @RequestMapping(value="/photos/{albumId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPhotosFromAlbum(@PathVariable Long albumId) {
        return photoService.getPhotosFromAlbum(albumId);
    }

    // CODES FOR THYMELEAF TESTING

    @GetMapping("/")
    public String UploadPage(Model model) {
        Photo photo = new Photo();
        Album album = new Album();
        model.addAttribute("photo", photo);
        model.addAttribute("album", album);
        return "home";
    }

    @PostMapping("/tl/photos")
    public String createPhotoTl(Photo photo, @RequestParam("albumId") Long albumId, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        // associate an album with the corresponding id: albumId
        Album album = albumRepository.findById(albumId).get();

        // create a new photo to be associated with the created album by using the setAlbum() method
        Photo newPhoto = new Photo();

        newPhoto.setTitle(photo.getTitle());
        newPhoto.setContent(photo.getContent());
        newPhoto.setAlbum(album);

        // first save is here, just to get the photo Id for the codes below
        photoRepository.save(newPhoto);

        // for cleaning the file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        newPhoto.setPhotoFileName(newPhoto.getId() + fileName); //add photoFileName property in the Photo model

        photoRepository.save(newPhoto); // -> at this point, may record na lalabas sa database!

        // codes below are for uploading the files in the file system (which will be exposed later on)
        String uploadDir = "./user-photos/" + newPhoto.getAlbum().getUser().getId() + "/" + newPhoto.getAlbum().getId();

        // IMPORTANT NOTE: added the ' + newPhoto.getId() ' to somehow create a unique image after every upload
        FileUploadUtil.saveFile(uploadDir, newPhoto.getId() + fileName, multipartFile);
        // we're going to create a FileUploadUtil class

        return "result";
    }

    // form/page for asking the user for an albumId input, where upon clicking the submit button, the user is directed to the list of images pertaining to the albumId inputted
    @GetMapping("/tl/form/getUserId")
    public String formGetAlbumId(Model model) {
        Photo photo = new Photo();
        Album album = new Album();
        User user = new User();
        model.addAttribute("photo", photo);
        model.addAttribute("album", album);
        model.addAttribute("user", user);
        return "formGetUserId";
    }

    // displaying photos from an album with id: albumId
    @GetMapping("/tl/display/albums")
    public String displayGetPhotosFromAlbum(@RequestParam("userId") Long userId, Model model) {
        // first, create an Array List
        ArrayList<Long> albumArrId = new ArrayList<>();
        ArrayList<String> albumArrName = new ArrayList<>(); // additional code

        // loop through every album with user_id = userId from the @RequestParam
        for (Album album : albumRepository.findAll()) {
            if (album.getUser().getId() == userId) {
                albumArrId.add(album.getId());
                albumArrName.add(album.getAlbumName()); // additional code
            }
        }

        // loop through every photo in each given album_id in the albumArr

        // create an Array List first
        ArrayList<ArrayList<String>> nestedArr = new ArrayList<>();
        HashMap<String, ArrayList<String>> nestedHashMap = new HashMap<>(); // additional code
        // possible additional code:
        // HashMap<String, HashMap<Photo, ArrayList<String>>> nestedHashMap = new HashMap<>();

        // generating empty elements for nestedArr based on the number of albums belonging to user with id = userId
        for (int i = 0; i < albumArrId.size(); i++) {
            nestedArr.add(new ArrayList<>());
        }

        // generating empty elements for nestedHashMap based on the number of albums belonging to user with id = userId
        for (int i = 0; i < albumArrId.size(); i++) {
            nestedHashMap.put("", new ArrayList<>());
        }

        for (int i = 0; i < albumArrId.size(); i++) {
            for (Photo photo : photoRepository.findAll()) {
                if (photo.getAlbum().getId() == albumArrId.get(i)) {
                    nestedArr.get(i).add("/user-photos/" + photo.getAlbum().getUser().getId() + "/" + photo.getAlbum().getId() + "/" + photo.getPhotoFileName());
                }
            }
        }

        // NEED A WAY TO ASSOCIATE ELEMENTS IN albumArrName WITH nestedArr ELEMENTS
        for (int i = 0; i < nestedArr.size(); i++) {
            nestedHashMap.put(albumArrName.get(i), nestedArr.get(i));
        }

        // adding nestedArr to the model to be able to use it in ThymeLeaf
        model.addAttribute("nestedArr", nestedArr);
        model.addAttribute("nestedHashMap", nestedHashMap);
        model.addAttribute("albumArrName", albumArrName);
        // nestedArr sample content: urlStringArr1, urlStringArr2, urlStringArr3, ...

        return "displayPhotos";

    }

}
