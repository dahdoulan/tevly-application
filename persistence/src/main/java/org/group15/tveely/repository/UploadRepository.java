package org.group15.tveely.repository;

import org.group15.tveely.entities.VideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends CrudRepository<VideoEntity, Long> {
}
