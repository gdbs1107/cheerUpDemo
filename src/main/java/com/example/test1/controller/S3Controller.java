package com.example.test1.controller;

import com.example.test1.controller.dto.ImageUploadDTO;
import com.example.test1.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class S3Controller {

    @Autowired
    private S3Service s3Service;




    @PostMapping(path = "/teams", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadPetImage(
            @RequestPart(value = "fileName") String fileName,
            @RequestPart(value = "file", required = false) MultipartFile multipartFile
    ) throws IOException {
        String extend = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        ImageUploadDTO result = s3Service.upload(fileName,multipartFile,extend);
        log.info(result.getUrl());
        return new ResponseEntity(result.getUrl(),null, HttpStatus.OK);
    }






    @GetMapping(path = "/teams/{fileName}")
    public ResponseEntity<byte[]> getPetImage(
            @PathVariable String fileName
    ) throws IOException {
        return s3Service.download(fileName);
    }





    @DeleteMapping(path = "/teams/{imageId}")
    public ResponseEntity<Void> deletePetImage(
            @PathVariable Long imageId
    ) {
        try {
            s3Service.deleteImage(imageId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("이미지 삭제 실패: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
