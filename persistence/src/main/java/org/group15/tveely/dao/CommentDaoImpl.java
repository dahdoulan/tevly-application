package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.CommentEntity;
import org.group15.tveely.repository.CommentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CommentDaoImpl implements CommentDao {
    private final CommentRepository commentRepository;

    @Override
    public void save(CommentEntity commentEntity) {
        commentRepository.save(commentEntity);
    }

    @Override
    public List<CommentEntity> findByVideoId(Long videoId) {
        return commentRepository.findByVideoId(videoId);
    }


}
