package org.group15.tveely.repository;

import org.group15.tveely.DTOs.VideoMetadata;
import org.group15.tveely.VideoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, Long> {
    @EntityGraph(attributePaths = {"categoryEntity"})
    List<VideoMetadata> findByStatus(String status);

}
