package com.sd.demo.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.demo.dao.CommentDao;
import com.sd.demo.dao.PlaceDao;
import com.sd.demo.dao.SysUserDao;
import com.sd.demo.entity.Comment;
import com.sd.demo.entity.SysUser;
import com.sd.demo.service.CommentService;
import com.sd.demo.service.PlaceService;
import com.sd.demo.web.CommentItem;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private SysUserDao userDao;
	@Override
	public List<CommentItem> getCommentsByPlace(int id) {
		if(!placeDao.existsById((long)id))return null;
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
	@Override
	public boolean addNewComment(long editorid, int placeid, String content) {
		if(!placeDao.existsById((long)placeid) || !userDao.existsById((long)editorid))return false;
		SysUser editor = userDao.getOne(editorid);
		Comment newComment = new Comment();
		newComment.setContent(content);
		newComment.setEditor(editor);
		newComment.setPlace(placeService.getPlaceDetail(placeid));
		commentDao.saveAndFlush(newComment);
		return true;
	}
}
