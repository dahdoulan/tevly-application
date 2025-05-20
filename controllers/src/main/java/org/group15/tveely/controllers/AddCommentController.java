package org.group15.tveely.controllers;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group15.tveely.DTOs.comment.CommentRequestDTO;
import org.group15.tveely.mappers.CommentMapper;
import org.group15.tveely.spi.SaveCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class AddCommentController {

    private final SaveCommentService saveCommentService;
    private final CommentMapper commentMapper;

    @PostMapping("/api/video/comment")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> saveComment(@RequestBody @Valid CommentRequestDTO request) throws MessagingException {
        // Get the authenticated user's email from SecurityContext
        String authenticatedEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Email: {}", authenticatedEmail);

        // Override the email inside the request (don't trust the client's input)
        request.setEmail(authenticatedEmail);

        // Now your CommentRequestDTO has the correct email
        saveCommentService.saveComment(commentMapper.toModel(request)); // If you have a save method

        return ResponseEntity.accepted().build();
    }


}


