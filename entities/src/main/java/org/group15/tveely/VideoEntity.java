package org.group15.tveely;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "video", indexes = {
        @Index(name = "idx_videos_title", columnList = "title"),
        @Index(name = "idx_videos_status", columnList = "status"),
        @Index(name = "idx_videos_upload_date", columnList = "upload_date")
})
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "upload_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime uploadDate;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "description", length = 500)
    private String description;


    @Column(name = "video_url", nullable = false, length = 255)
    private String videoUrl;

    @Column(name = "processing_path", length = 255)
    private String processingPath;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Lob
    @Column(name = "thumbnail")
    private Blob thumbnail;

    @Column(name = "thumbnail_url", nullable = false, length = 255)
    private String thumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_video_category"))
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filmmaker_id", nullable = false, foreignKey = @ForeignKey(name = "fk_video_filmmaker"))
    private UserEntity filmmaker;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "video")
    private List<CommentEntity> commentEntities;

    @OneToMany(mappedBy = "video")
    private List<RatingEntity> ratingEntities;

    @Column(name = "average_rating")
    private int averageRating;

    @OneToMany(mappedBy = "video")
    private List<EncodedVideoEntity> encodedVideos;
}
