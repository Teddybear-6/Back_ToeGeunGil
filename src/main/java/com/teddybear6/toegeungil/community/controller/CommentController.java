package com.teddybear6.toegeungil.community.controller;

import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.community.dto.CommentDTO;
import com.teddybear6.toegeungil.community.entity.Comment;
import com.teddybear6.toegeungil.community.service.CommentService;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/communitys")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "커뮤니티 댓글 Api", tags = {"03.1. Community Comment Info"}, description = "커뮤니티 댓글 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class CommentController {

    private final CommentService commentService;
    private final UserViewService userViewService;

    public CommentController(CommentService commentService, UserViewService userViewService) {
        this.commentService = commentService;
        this.userViewService = userViewService;
    }

    @GetMapping("/comments/{communityNum}")
    @ApiOperation(value = "커뮤니티별 댓글 목록 조회 Api")
    public ResponseEntity<List<?>> CommentByCommunityNum(@PathVariable int communityNum){
        List<CommentDTO> commentDTOList = commentService.CommentByCommunityNum(communityNum);

        if(commentDTOList.size() == 0){
            List<String> error = new ArrayList<>();
            error.add("해당 게시글에 댓글이 존재하지 않습니다.");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(commentDTOList);
    }
    @PostMapping("/comments/{communityNum}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    @ApiOperation(value = "커뮤니티 댓글 작성 Api")
    public ResponseEntity<?> registCommentForCommunity(@PathVariable int communityNum, @RequestBody CommentDTO commentDTO){

        commentDTO.setCommentWriteDate(new Date());

        int result = 0;
        System.out.println(commentDTO);
        try {
            result = commentService.registCommentForCommunity(communityNum, commentDTO);
        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("댓글 등록에 실패하였습니다.");
        }

        if (result > 0) {
            return ResponseEntity.ok().body("댓글 등록에 성공하였습니다.");
        }else {
            return ResponseEntity.status(500).body("댓글 등록에 실패하였습니다.");
        }
    }
    @PutMapping("/comments/{communityNum}/{commentNum}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    @ApiOperation(value = "커뮤니티 댓글 수정 Api")
    public ResponseEntity<?> updateComment(@PathVariable int communityNum, @PathVariable int commentNum, @RequestBody CommentDTO commentDTO, @AuthenticationPrincipal AuthUserDetail userDetail){

        int result = commentService.updateCommentByCommunity(communityNum, commentNum, commentDTO);



        if(result == 1){
            return ResponseEntity.ok().body("댓글 수정에 성공하였습니다.");
        } else if (result == 0) {
            return ResponseEntity.status(404).body("댓글을 찾을 수 없습니다.");
        } else {
            return ResponseEntity.status(500).body("댓글 삭제에 실패하였습니다.");
        }
    }

    @DeleteMapping("/comments/{communityNum}/{commentNum}")
    @ApiOperation(value = "커뮤니티 댓글 삭제 Api")
    public ResponseEntity<?> deleteComment(@PathVariable int communityNum, @PathVariable int commentNum){

        int result = commentService.deleteCommentByCommunity(communityNum, commentNum);

        if(result == 1){
            return ResponseEntity.ok().body("댓글 삭제에 성공하였습니다.");
        } else if (result == 0) {
            return ResponseEntity.status(404).body("댓글을 찾을 수 없습니다.");
        } else {
            return ResponseEntity.status(500).body("댓글 삭제에 실패하였습니다.");
        }
    }
}







