package org.group15.tveely.services;

import lombok.AllArgsConstructor;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.ffmpeg.FfmpegWrapper;
import org.group15.tveely.spi.EncodingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import static org.group15.tveely.ffmpeg.FfmpegWrapper.BASH;
import static org.group15.tveely.ffmpeg.FfmpegWrapper.CMD;

@Service
@AllArgsConstructor
public class EncodingServiceImpl implements EncodingService{
    private final VideoDao videoDao;
    private final FfmpegWrapper build = FfmpegWrapper.builder()
            .scale("1920:1080")
            .terminal(BASH)
            .expected("-c")
            .build();


    @Scheduled(fixedRate = 5000)
    @Override
    public void encode() {
        videoDao.findVideoByStatus("NEW").stream().forEach(video -> {build.encode()});
    }
}
