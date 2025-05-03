package org.group15.tveely.mappers;

import org.group15.tveely.Video;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Component
public class MultipartToVideo {
    public Video map(MultipartFile file, String title, String description, MultipartFile thumbnail, String category, String email) throws IOException {


        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setStatus("NEW");
        video.setContent(file.getBytes());
        video.setVideoUrl(Create_Unique_Filename(file));
        video.setCategory(category);
        video.setThumbnailUrl(Create_Unique_Filename(thumbnail));
        video.setThumbnail(thumbnail.getBytes());

        video.setUploadDate(LocalDateTime.now());
        video.setFilmmakerEmail(email);
        return video;
    }


    public String Create_Unique_Filename(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (nonNull(originalFilename)) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            return UUID.randomUUID() + "_" + System.currentTimeMillis() + fileExtension;
        }
        throw new IllegalArgumentException("original Filename is empty");


    }
}
