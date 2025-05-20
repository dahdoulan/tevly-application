package org.group15.tveely;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "ratings")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rating_User"))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rating_video"))
    private VideoEntity video;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "create_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate;
}