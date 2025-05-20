package org.group15.tveely.repository;

import org.group15.tveely.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<VideoEntity, Long> {
}
