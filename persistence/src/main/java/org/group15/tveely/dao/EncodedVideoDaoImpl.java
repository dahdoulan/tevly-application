package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.mappers.EncodedVideoAdapterToEncodedVideoEntity;
import org.group15.tveely.mappers.EncodedVideoEntityToEncodedVideo;
import org.group15.tveely.mappers.VideoEntityToVideo;
import org.group15.tveely.models.EncodedVideoAdapter;
import org.group15.tveely.repository.EncodedVideoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EncodedVideoDaoImpl implements EncodedVideoDao {

    private EncodedVideoRepository encodedVideoRepository;

    @Override
    public void saveVideo(EncodedVideoAdapter encodedVideo) {
        EncodedVideoAdapterToEncodedVideoEntity mapper
                = new EncodedVideoAdapterToEncodedVideoEntity();
        encodedVideoRepository.save(mapper.map(encodedVideo));
    }

    @Override
    public List<EncodedVideoAdapter> getVideosById(Long id) {
        return encodedVideoRepository
                .findAllByVideoId(id)
                .stream()
                .map(EncodedVideoEntityToEncodedVideo::map)
                .toList();
    }
}
