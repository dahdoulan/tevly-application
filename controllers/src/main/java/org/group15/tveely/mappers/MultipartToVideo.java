package org.group15.tveely.mappers;

import org.group15.tveely.models.Video;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class MultipartToVideo {
    public Video map (MultipartFile file, String title, String description) throws IOException {
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setStatus("NEW");
        video.setContent(file.getBytes());
        video.setVideoUrl(file.getOriginalFilename());
        video.setUploadDate(LocalDateTime.now());
        return video;
    }
}
