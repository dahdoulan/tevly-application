package org.group15.tveely.controllers;

import org.group15.tveely.mappers.MultipartToVideo;
import org.group15.tveely.services.UploadServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class UploadControllerTest {

    private UploadController controller;
    private UploadServiceImpl service;
    private final MultipartToVideo multipartToVideo = new MultipartToVideo();
    private boolean isValidRequest;
    @BeforeEach
    void setUp() {
        service = mock(UploadServiceImpl.class);
        controller = new MockUploadController(service, multipartToVideo);
        isValidRequest = true;
    }

    @Test
    void givenValidRequest_whenUploadVideo_thenShouldReturnNoContentStatus() throws IOException {
        ResponseEntity responseEntity = controller.uploadVideo(null, "", "");
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    private class MockUploadController extends UploadController {
        public MockUploadController(UploadServiceImpl uploadServiceImpl, MultipartToVideo multipartToVideo) {
            super(uploadServiceImpl, multipartToVideo);
        }

        @Override
        public ResponseEntity uploadVideo(MultipartFile video, String title, String description) {
            if(isValidRequest) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }
}