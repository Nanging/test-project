package com.sd.demo.service;

import java.util.List;

import com.sd.demo.entity.Comment;
import com.sd.demo.entity.SysUser;
import com.sd.demo.web.CommentItem;

public interface CommentService {

	List<CommentItem> getCommentsByPlace(int id);

	Comment addNewComment(SysUser editor, int placeid, String content);

}
