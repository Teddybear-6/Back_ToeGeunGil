package com.teddybear6.toegeungil.community.service;

import com.teddybear6.toegeungil.community.dto.CommentDTO;
import com.teddybear6.toegeungil.community.entity.Comment;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.repository.CommentRepository;
import com.teddybear6.toegeungil.community.repository.CommunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommunityRepository communityRepository;

    public CommentService(CommentRepository commentRepository, CommunityRepository communityRepository) {
        this.commentRepository = commentRepository;
        this.communityRepository = communityRepository;
    }

    // 커뮤니티 번호로 댓글 찾기
    public List<CommentDTO> CommentByCommunityNum(int communityNum) {

        List<Comment> comments = commentRepository.findByCommunityNum(communityNum);

        List<CommentDTO> commentDTOList = comments.stream()
                .map(m-> new CommentDTO(m)
                ).collect(Collectors.toList());

        return commentDTOList;
    }

    // 댓글 등록
    @Transactional
    public int registCommentForCommunity(int communityNum, CommentDTO commentDTO) throws IOException{

        Community community = communityRepository.findById(communityNum);

        if(community == null){
            return 0;
        }

        Comment comment = new Comment();
        comment.setUserNum(commentDTO.getUserNum());
        comment.setCommunityNum(communityNum);
        comment.setCommentDetail(commentDTO.getCommentDetail());
        comment.setCommentWriteDate(new Date());

        Comment savedComment = commentRepository.save(comment);

        if(savedComment != null){
            return 1;
        }else {
            return 0;
        }
    }

    // 댓글 수정
    @Transactional
    public int updateCommentByCommunity(int communityNum, int commentNum, CommentDTO commentDTO) {

        Comment comment = commentRepository.findByCommunityNumAndCommentNum(communityNum, commentNum);

        if(comment == null){
            return 0;
        }

        comment.setCommentDetail(commentDTO.getCommentDetail());
        comment.setCommentUpdateDate(new Date());

        Comment updateComment = commentRepository.save(comment);

        if (updateComment != null){
            return 1;
        }else {
            return 0;
        }
    }

    // 댓글 삭제
    @Transactional
    public int deleteCommentByCommunity(int communityNum, int commentNum) {
        Comment deleteComment = commentRepository.findByCommunityNumAndCommentNum(communityNum, commentNum);

        if (deleteComment != null){
            commentRepository.delete(deleteComment);
            return 1;
        }else {
            return 0;
        }
    }

}



