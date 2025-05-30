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

@ExtendWith(MockitoExtension.class)
class SaveCommentDtoImplServiceImplTest {

    @Mock
    private CommentDao commentDao;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private SaveCommentServiceImpl service;

    @Test
    void saveComment_ShouldMapAndSaveEntity() {
        // Arrange
        CommentDtoImpl commentDtoImpl = new CommentDtoImpl();
        commentDtoImpl.setComment("Great video!");
        commentDtoImpl.setUserId(123L);
        commentDtoImpl.setVideoId(456L);

        UserEntity user = new UserEntity();
        user.setId(123L);

        VideoEntity video = new VideoEntity();
        video.setId(456L);

        CommentEntity expectedEntity = new CommentEntity();
        expectedEntity.setComment("Great video!");
        expectedEntity.setUser(user);
        expectedEntity.setVideo(video);

        when(commentMapper.toEntity(commentDtoImpl)).thenReturn(expectedEntity);

        // Act
        service.saveComment(commentDtoImpl);

        // Assert
        verify(commentMapper).toEntity(commentDtoImpl);
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