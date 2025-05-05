package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.comment.CommentResponseDTO;
import org.group15.tveely.DTOs.videometadata.VideoMetadata;
import org.group15.tveely.DTOs.videometadata.VideoMetadataDTO;
import org.group15.tveely.dao.CommentDao;
import org.group15.tveely.dao.RatingDao;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.spi.HomePageMetadataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HomePageMetadataServiceImpl implements HomePageMetadataService {
    private final VideoDao<Video> videoDao;
    private final CommentDao commentDao; // Add CommentDao dependency
    private final RatingDao ratingDao; // Add CommentDao dependency
    private final UserDao userDao; // Add UserDao dependency

    @Override
    @Transactional(readOnly = true)
    public List<VideoMetadataDTO> getHomePageMetadata(String email) {
        List<VideoMetadata> approvedVideos = videoDao.findByStatus("APPROVED");
        UserEntity userEntity = userDao.findByEmail(email).orElse(null);

        return approvedVideos.stream()
                .map(video -> {
                    List<CommentEntity> comments = commentDao.findByVideoId(video.getId());
                    List<CommentResponseDTO> commentDTOs = comments.stream()
                            .map(comment -> new CommentResponseDTO(
                                    comment.getComment(),
                                    comment.getUser().getFullName(),
                                    comment.getCreateDate()))
                            .collect(Collectors.toList());

                    // Correctly fetch rating for THIS video
                    RatingEntity ratingEntity = userEntity != null
                            ? ratingDao.findByUserIdAndVideoId(userEntity.getId(), video.getId()).orElse(null)
                            : null;

                    int userRating = ratingEntity != null ? ratingEntity.getRating() : 0;

                    return new VideoMetadataDTO(
                            video.getId(),
                            video.getTitle(),
                            video.getDescription(),
                            video.getVideoUrl(),
                            video.getCategoryEntity(),
                            commentDTOs,
                            video.getAverageRating(),
                            userRating
                    );
                })
                .collect(Collectors.toList());
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
