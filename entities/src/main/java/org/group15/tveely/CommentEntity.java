package org.group15.tveely;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_Comment_User"))
    private UserEntity user;  // <--- changed from Long userId to UserEntity user

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_video"))
    private VideoEntity video;

    @Column(name = "comment", nullable = false, columnDefinition = "TEXT")
    private String comment;

    @CreatedDate
    @Column(name = "create_date",nullable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate;
}
