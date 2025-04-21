package org.group15.tveely.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.DTOs.VideoMetadata;
import org.group15.tveely.spi.HomePageMetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
}
