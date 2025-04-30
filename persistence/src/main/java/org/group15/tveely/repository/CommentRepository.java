package org.group15.tveely.repository;

import org.group15.tveely.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByVideoId(Long videoId);


}
