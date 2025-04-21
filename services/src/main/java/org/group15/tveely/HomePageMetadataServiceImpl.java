package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.DTOs.VideoMetadata;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.spi.HomePageMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HomePageMetadataServiceImpl implements HomePageMetadataService {
    private final VideoDao<?>  videoDao;

    @Override
    public List<VideoMetadata> getHomePageMetadata() {

        return videoDao.findByStatus("APPROVED");
    }

}
