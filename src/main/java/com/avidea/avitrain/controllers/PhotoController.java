package com.avidea.avitrain.controllers;

import com.avidea.avitrain.error.ResponseMessage;
import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.FileInfo;
import com.avidea.avitrain.models.Photo;
import com.avidea.avitrain.services.ClaimService;
import com.avidea.avitrain.services.FilesStorageService;
import com.avidea.avitrain.services.PhotoService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/claims/{claimId}/photos")
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200", maxAge = 3600)
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private ClaimService claimService;
    @Autowired
    FilesStorageService storageService;

    private Long claimId;
    private Claim getThisClaim(){
        return claimService.getClaim(this.claimId);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<FileInfo> getPhoto(@PathVariable("id") String fname){
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(PhotoController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos.get(0));
    }
    @GetMapping("/{filename}")
    @ResponseBody
    public byte[] getFileSrc(@PathVariable String filename) throws IOException {
        Resource file = storageService.load(filename);
        return IOUtils.toByteArray(file.getInputStream());
    }


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

//            List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
//                String filename = path.getFileName().toString();
//                String url = MvcUriComponentsBuilder
//                        .fromMethodName(PhotoController.class, "getFile", path.getFileName().toString()).build().toString();
//
//                return new FileInfo(filename, url);
//            }).collect(Collectors.toList());
//            return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//
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
            //TODO: delete the file location in the storrage
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    // Add files upload
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @PathVariable("claimId") Long claimId) {
        String message = "";
        try {
            storageService.save(file);
            Photo photo = new Photo(file.getName(), file.getContentType(),
                    "http://localhost:8080/claims/"+claimId.toString()+"/photos/files/"+file.getOriginalFilename(),
                    claimService.getClaim(claimId));
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            photo.setTitle(file.getName());
            photo.setDescription(file.getContentType());
            photo.setLink(MvcUriComponentsBuilder
                    .fromMethodName(PhotoController.class, "getFile", file.getOriginalFilename().toString()).build().toString());
            photoService.addPhoto(photo);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(PhotoController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
