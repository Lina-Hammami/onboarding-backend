package com.avidea.avitrain.services;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Photo;
import com.avidea.avitrain.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PhotoService {
    @Autowired
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }
    public List<Photo> getPhotos(){
        return photoRepository.findAll();
    }
    public Photo getPhoto(long id) {
        return photoRepository.findById(id).orElse(null);
    }
    public Photo addPhoto(Photo photo){
        return photoRepository.save(photo);
    }
    public void deletePhoto(long id){
        photoRepository.deleteById(id);
    }
    public List<Photo> getPhotosByClaimId(Claim claim) {
        return photoRepository.findAllByClaim(claim);
    }
}
