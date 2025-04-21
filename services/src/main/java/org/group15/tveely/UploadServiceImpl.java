package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.dao.UploadDao;
import org.group15.tveely.spi.UploadService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UploadServiceImpl implements UploadService<Video> {

    private final UploadDao<Video> uploadDao;

    @Override
    public void uploadVideo(Video video) {
        uploadDao.uploadVideo(video);
    }
}
