package org.group15.tveely.mappers;

import lombok.RequiredArgsConstructor;
import org.group15.tveely.CommentDtoImpl;
import org.group15.tveely.CommentEntity;
import org.group15.tveely.DTOs.comment.CommentRequestDTO;
import org.group15.tveely.UserEntity;
import org.group15.tveely.VideoEntity;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.dto.CommentDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final UserDao userDao;
    public CommentEntity toEntity(CommentDto comment) {
        CommentEntity entity = new CommentEntity();

        entity.setUser(userDao.findById(comment.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));

        // Video mapping
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId(comment.getVideoId());
        entity.setVideo(videoEntity);

        entity.setComment(comment.getComment());
        entity.setCreateDate(comment.getCreateDate());

        return entity;
    }


    public CommentDtoImpl toModel(CommentRequestDTO commentRequestDTO) {
        UserEntity userEntity = userDao.findByEmail(commentRequestDTO.getEmail())
                .orElse(null); // or throw an exception if not found

        return new CommentDtoImpl(
                userEntity.getId(),
                commentRequestDTO.getVideoId(),
                commentRequestDTO.getComment(),
                LocalDateTime.now()
        );
    }


}
