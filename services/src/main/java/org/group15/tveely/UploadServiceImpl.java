package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.dao.UserDao;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.spi.UploadService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UploadServiceImpl implements UploadService<Video> {

    private final VideoDao<Video> videoDao;
    private final UserDao userDao;
    @Override
    public void uploadVideo(Video video) {

        videoDao.uploadVideo(video);
    }
}
