package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.DTOs.ReviewingContentResponse;
import org.group15.tveely.dao.VideoDao;
import org.group15.tveely.mappers.ReviewingContentMapper;
import org.group15.tveely.spi.ReviewingContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewingContentServiceImpl implements ReviewingContentService {
    private final VideoDao<Video> videoDao;
    private final ReviewingContentMapper mapper;


    @Override
    public List<ReviewingContentResponse> getAllReviewingContent() {
        List<VideoEntity> videoEntities = videoDao.findVideoEntityByStatus("ENCODING");
        List<ReviewingContentResponse> reviewingContentResponses = videoEntities.stream()
                .map(mapper::toDto)
                .toList();

        return reviewingContentResponses;
    }

    @Override
    public void approveContent(Long contentId) {
        videoDao.updateVideoStatus(contentId, "APPROVED");
    }

    @Override
    public void rejectContent(Long contentId) {
        videoDao.updateVideoStatus(contentId, "REJECTED");
    }

}
