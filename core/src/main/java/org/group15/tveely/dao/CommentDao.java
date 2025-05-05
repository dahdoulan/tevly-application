package org.group15.tveely.dao;

import org.group15.tveely.CommentEntity;

import java.util.List;

public interface CommentDao {

    void save(CommentEntity commentEntity);
    List<CommentEntity> findByVideoId(Long videoId);
}
