package org.group15.tveely.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.mappers.MultipartToVideo;
import org.group15.tveely.services.UploadServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@AllArgsConstructor
@RestController
public class UploadController {

    private final UploadServiceImpl uploadServiceImpl;
    private final MultipartToVideo multipartToVideo;

    @PostMapping("/api/video/upload")
    public ResponseEntity uploadVideo(@RequestParam(name = "video") MultipartFile video, @RequestParam(name = "title") String title, @RequestParam(name = "description") String description) {
        try{
            uploadServiceImpl.uploadVideo(multipartToVideo.map(video, title, description));
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            log.error("ERROR WHILE UPLOADING VIDEO | ERROR MESSAGE : {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
