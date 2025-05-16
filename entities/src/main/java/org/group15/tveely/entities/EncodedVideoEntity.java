package org.group15.tveely.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "encoded_video", indexes = {
    @Index(name = "idx_encoded_video", columnList = "id")
})
public class EncodedVideoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private VideoEntity video;

    @Column(name = "encoded_video_url", nullable = false, length = 255)
    private String encodedVideoUrl;

    @Column(name = "encoded_video_title", nullable = false, length = 255)
    private String encodedVideoTitle;
}