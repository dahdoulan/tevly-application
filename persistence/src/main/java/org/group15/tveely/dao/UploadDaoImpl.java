package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.mappers.VideoToVideoEntity;
import org.group15.tveely.Video;
import org.group15.tveely.repository.UploadRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UploadDaoImpl implements UploadDao<Video> {

    private final UploadRepository uploadRepository;
    private final VideoToVideoEntity videoToVideoEntity;

    @Override
    public void uploadVideo(Video videoEntity) {
            uploadRepository.save(videoToVideoEntity.map(videoEntity));
    }
}
