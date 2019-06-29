package com.sd.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.entity.Comment;
import com.sd.demo.entity.PlaceImage;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
	List<Comment> findByPlace(int place);
}
