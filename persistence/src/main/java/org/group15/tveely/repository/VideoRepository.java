package org.group15.tveely.repository;

import org.group15.tveely.DTOs.ThumbnailProjection;
import org.group15.tveely.DTOs.VideoMetadata;
import org.group15.tveely.VideoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, Long> {
    List<VideoEntity> findVideoEntitiesByStatus(String status);

    @Modifying
    @Transactional
    @Query("UPDATE VideoEntity e SET e.status = :status WHERE e.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") String status);

    @EntityGraph(attributePaths = {"categoryEntity"})
    List<VideoMetadata> findByStatus(String status);

    @Query("SELECT thumbnail AS thumbnail FROM VideoEntity WHERE id = :id")
    Optional<ThumbnailProjection> findThumbnailById(@Param("id") Long id);
}
