package org.group15.tveely.entities;

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

    @Column(name = "thumbnail_url", length = 255)
    private String thumbnailUrl;

    @Column(name = "video_url", nullable = false, length = 255)
    private String videoUrl;

    @Lob
    @Column(name = "content")
    private Blob content;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "video")
    private List<Comment> comments;

    @OneToMany(mappedBy = "video")
    private List<Review> reviews;
}
