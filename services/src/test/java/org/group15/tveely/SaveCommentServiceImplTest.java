package org.group15.tveely;

import org.group15.tveely.dao.CommentDao;
import org.group15.tveely.mappers.CommentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveCommentServiceImplTest {

    @Mock
    private CommentDao commentDao;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private SaveCommentServiceImpl service;

    @Test
    void saveComment_ShouldMapAndSaveEntity() {
        // Arrange
        Comment comment = new Comment();
        comment.setComment("Great video!");
        comment.setUserId(123L);
        comment.setVideoId(456L);

        UserEntity user = new UserEntity();
        user.setId(123L);

        VideoEntity video = new VideoEntity();
        video.setId(456L);

        CommentEntity expectedEntity = new CommentEntity();
        expectedEntity.setComment("Great video!");
        expectedEntity.setUser(user);
        expectedEntity.setVideo(video);

        when(commentMapper.toEntity(comment)).thenReturn(expectedEntity);

        // Act
        service.saveComment(comment);

        // Assert
        verify(commentMapper).toEntity(comment);
        verify(commentDao).save(expectedEntity);
    }

    private UserEntity createUser(Long id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }

    private VideoEntity createVideo(Long id) {
        VideoEntity video = new VideoEntity();
        video.setId(id);
        return video;
    }
}