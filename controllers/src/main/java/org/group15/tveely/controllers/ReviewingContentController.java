package org.group15.tveely.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.DTOs.ReviewingContentResponse;
import org.group15.tveely.spi.ReviewingContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class ReviewingContentController {
    private final ReviewingContentService reviewingContentService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/api/reviewing-content")
    public ResponseEntity<List<ReviewingContentResponse>> getAllReviewingContent() {
        log.info("Fetching all reviewing content");
        List<ReviewingContentResponse> result = reviewingContentService.getAllReviewingContent();
        log.debug("Found {} reviewing content entries", result.size());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/api/reviewing-content/approve")
    public ResponseEntity<Void> approveContent(@RequestParam(name = "videoId") Long videoId) {
        log.info("Approving content with ID: {}", videoId);
        reviewingContentService.approveContent(videoId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/api/reviewing-content/reject")
    public ResponseEntity<Void> rejectContent(@RequestParam(name = "videoId") Long videoId) {
        log.info("Approving content with ID: {}", videoId);
        reviewingContentService.rejectContent(videoId);
        return ResponseEntity.ok().build();
    }



    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/api/reviewing/rejected/content")
    public ResponseEntity<List<ReviewingContentResponse>> getAllRejectContent() {
        log.info("Fetching all rejected reviewing content");
        List<ReviewingContentResponse> result = reviewingContentService.getAllRejectContent();
        log.debug("Found {} reviewing content entries", result.size());
        return ResponseEntity.ok(result);
    }


}
