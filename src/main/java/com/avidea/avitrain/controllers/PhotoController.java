package com.avidea.avitrain.controllers;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Photo;
import com.avidea.avitrain.services.ClaimService;
import com.avidea.avitrain.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/claims/{claimId}/photos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private ClaimService claimService;
    @GetMapping("/list")
    public ResponseEntity<List<Photo>> getPhotosByClaimId(@PathVariable("claimId") Long claimId){
        try {
            Claim claim = claimService.getClaim(claimId);
            List<Photo> photos = claim.getPhotos();

            if (claim!=null){
                photos = claim.getPhotos();
            }
            if (photos==null || photos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(photos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Photo>  addPhoto(@RequestBody Photo photo)  {
        try {
            Photo c = photoService.addPhoto(photo);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Photo>  updatePhoto(@RequestBody Photo photo, @PathVariable long id)  {
        try {
            Photo c = photoService.updatePhoto(photo, id);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus>  deletePhoto(@PathVariable long id) {
        try {
            photoService.deletePhoto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
