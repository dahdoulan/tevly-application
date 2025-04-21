package org.group15.tveely.repository;

import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.VideoMetadata;
import org.group15.tveely.VideoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, Long> {
    @EntityGraph(attributePaths = {"categoryEntity"})
    List<VideoMetadata> findByStatus(String status);

    @Query("SELECT thumbnail AS thumbnail FROM VideoEntity WHERE id = :id")
    Optional<ThumbnailProjection> findThumbnailById(@Param("id") Long id);  // Add @Param
}
