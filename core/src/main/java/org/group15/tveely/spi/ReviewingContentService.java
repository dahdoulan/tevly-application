package org.group15.tveely.spi;

import org.group15.tveely.DTOs.ReviewingContentResponse;

import java.util.List;

public interface ReviewingContentService {
     List<ReviewingContentResponse> getAllReviewingContent();
     void approveContent(Long contentId);
     void rejectContent(Long contentId);
    }
