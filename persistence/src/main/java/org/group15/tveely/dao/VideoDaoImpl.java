package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.entities.VideoEntity;
import org.group15.tveely.mappers.VideoEntityToVideo;
import org.group15.tveely.models.VideoAdapter;
import org.group15.tveely.repository.VideoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
public class VideoDaoImpl implements VideoDao {
    private final VideoRepository videoRepository;

    @Override
    public List<VideoAdapter> findVideoByStatus(String status) {
        VideoEntityToVideo mapper = new VideoEntityToVideo();
        List<VideoEntity> entity = videoRepository.findByStatus(status);
        return entity.stream().map(mapper::map).toList();
    }

    @Override
    public void updateStatusById(String id, String status) {
        videoRepository.updateStatusById(id,status);
    }
}
