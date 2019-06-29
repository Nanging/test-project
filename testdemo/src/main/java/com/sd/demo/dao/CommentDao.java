package com.sd.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {

}
