package com.teddybear6.toegeungil.community.repository;

import com.teddybear6.toegeungil.community.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
