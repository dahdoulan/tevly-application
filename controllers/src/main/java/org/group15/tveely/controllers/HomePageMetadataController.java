package org.group15.tveely.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.DTOs.VideoMetadata;
import org.group15.tveely.spi.HomePageMetadataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@AllArgsConstructor
@RestController
public class HomePageMetadataController {
    private final HomePageMetadataService homePageMetadataService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/api/homepage/metadata")
    public ResponseEntity<List<VideoMetadata>> getHomePageMetadata() {
        log.info("Fetching homepage metadata");
        List<VideoMetadata> result = homePageMetadataService.getHomePageMetadata();
        log.debug("Found {} metadata entries", result.size());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/api/videos/{videoId}/thumbnail")
    public ResponseEntity<byte[]> getThumbnail(@PathVariable("videoId") Long videoId) {
        log.info("Fetching thumbnail for video ID: {}", videoId);
        byte[] thumbnailBytes = homePageMetadataService.getThumbnailBytes(videoId);
        log.debug("Returning thumbnail of size {} bytes for video ID: {}", thumbnailBytes.length, videoId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or MediaType.IMAGE_PNG based on your format
                .body(thumbnailBytes);
    }

}
