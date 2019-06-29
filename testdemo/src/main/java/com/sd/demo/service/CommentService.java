package com.sd.demo.service;

import java.util.List;

import com.sd.demo.web.CommentItem;

public interface CommentService {

	List<CommentItem> getCommentsByPlace(int id);

}
