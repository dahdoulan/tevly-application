package org.group15.tveely.repository;

import org.group15.tveely.entities.VideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, Long> {
    List<VideoEntity> findByStatus(String status);
}
