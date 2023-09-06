package com.teddybear6.toegeungil.community.service;

import com.teddybear6.toegeungil.community.dto.CommentDTO;
import com.teddybear6.toegeungil.community.entity.Comment;
import com.teddybear6.toegeungil.community.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDTO> CommentByCommunityNum(int communityNum) {

        List<Comment> comments = commentRepository.findByCommunityNum(communityNum);

        List<CommentDTO> commentDTOList = new ArrayList<>();

        return commentDTOList;
    }

}



