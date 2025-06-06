package org.group15.tveely;

import lombok.AllArgsConstructor;
import org.group15.tveely.dao.CommentDao;
import org.group15.tveely.mappers.CommentMapper;
import org.group15.tveely.dto.CommentDto;
import org.group15.tveely.spi.SaveCommentService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SaveCommentServiceImpl implements SaveCommentService {
    private final CommentDao commentDao;
    private final CommentMapper commentMapper;

    @Override
    public void saveComment(CommentDto comment) {
        commentDao.save(commentMapper.toEntity(comment));
    }


}
