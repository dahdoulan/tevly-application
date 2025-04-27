package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.VideoMetadata;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.spi.HomePageMetadataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;
@Service
@AllArgsConstructor
public class HomePageMetadataServiceImpl implements HomePageMetadataService {
    private final VideoDao<Video>  videoDao;

    @Override
    public List<VideoMetadata> getHomePageMetadata() {

        return videoDao.findByStatus("APPROVED");
    }

    @Override
    @Transactional // Add this annotation
    public byte[] getThumbnailBytes(Long videoId) {
        ThumbnailProjection projection = videoDao.findThumbnailById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        Blob thumbnailBlob = projection.getThumbnail();
        if (thumbnailBlob == null) {
            throw new RuntimeException("Thumbnail not found for video ID: " + videoId);
        }

        try (InputStream inputStream = thumbnailBlob.getBinaryStream()) {
            return inputStream.readAllBytes();
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Failed to read thumbnail", e);
        }
    }



}
