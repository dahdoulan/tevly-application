package org.group15.tveely.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.spi.EncodedVideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class EncodedVideoController {

    private final EncodedVideoService encodedVideoService;

    @PreAuthorize("hasAnyRole('FILMMAKER','ADMIN', 'USER')")
    @GetMapping("/api/video/getVideos")
    public ResponseEntity getVideo(@RequestParam("id") Long id) {
        try{
            return ResponseEntity.ok(encodedVideoService.getEncodedVideo(id));
        }catch (Exception e){
            log.error("ERROR WHILE GETTING VIDEO | ERROR MESSAGE : {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
