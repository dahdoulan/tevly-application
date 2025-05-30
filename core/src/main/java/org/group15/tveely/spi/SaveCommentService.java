package org.group15.tveely.spi;


import org.group15.tveely.dto.CommentDto;

public interface SaveCommentService {

    void saveComment(CommentDto comment);
}
