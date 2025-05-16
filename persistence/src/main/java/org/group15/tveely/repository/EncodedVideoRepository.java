package org.group15.tveely.repository;

import org.group15.tveely.entities.EncodedVideoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncodedVideoRepository extends CrudRepository<EncodedVideoEntity, Long> {
    List <EncodedVideoEntity> findAllByVideoId(Long videoId);
}
