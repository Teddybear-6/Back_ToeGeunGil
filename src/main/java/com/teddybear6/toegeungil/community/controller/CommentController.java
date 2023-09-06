package com.teddybear6.toegeungil.community.controller;

import com.teddybear6.toegeungil.community.dto.CommentDTO;
import com.teddybear6.toegeungil.community.entity.Comment;
import com.teddybear6.toegeungil.community.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/ments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

}



