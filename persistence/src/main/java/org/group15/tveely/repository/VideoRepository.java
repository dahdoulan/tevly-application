package org.group15.tveely.repository;

import jakarta.transaction.Transactional;
import org.group15.tveely.entities.VideoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<VideoEntity, Long> {
    List<VideoEntity> findByStatus(String status);

    @Modifying
    @Transactional
    @Query("UPDATE VideoEntity e SET e.status = :status WHERE e.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") String status);
}
