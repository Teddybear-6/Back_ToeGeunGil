package com.teddybear6.toegeungil.community.controller;

import com.teddybear6.toegeungil.community.dto.CommentDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/community/{communityNum}")
    public ResponseEntity<List<?>> CommentByCommunityNum(@PathVariable int communityNum){
        List<CommentDTO> commentDTOList = commentService.CommentByCommunityNum(communityNum);

        if(commentDTOList.size() == 0){
            List<String> error = new ArrayList<>();
            error.add("해당 게시글에 댓글이 존재하지 않습니다.");
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok().body(commentDTOList);
    }
}



