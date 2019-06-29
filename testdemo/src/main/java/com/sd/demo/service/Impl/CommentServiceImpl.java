package com.sd.demo.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.CommentDao;
import com.sd.demo.entity.Comment;
import com.sd.demo.service.CommentService;
import com.sd.demo.service.PlaceService;
import com.sd.demo.web.CommentItem;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private PlaceService placeService;
	
	@Override
	public List<CommentItem> getCommentsByPlace(int id) {
		Set<Comment> comments = placeService.getPlaceDetail(id).getComments();
		List<CommentItem> resultList = new ArrayList<>();
		for (Comment comment : comments) {
			CommentItem item = getCommentResult(comment);
			resultList.add(item);
		}
		return resultList;
	}
	public CommentItem getCommentResult(Comment comment) {
		CommentItem item = new CommentItem();
		item.setId((int)(long)comment.getId());
		item.setContent(comment.getContent());
		item.setTime(comment.getTime());
		item.setUsername(comment.getEditor().getUsername());
		return item;
	}
}