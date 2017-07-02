package com.niit.collaboration.dao;

import java.util.List;


import com.niit.collaboration.model.ForumComment;

public interface ForumCommentDao {

	
public boolean save(ForumComment forumcomment);
	
	public boolean update(ForumComment forumcomment);
	
	public boolean saveOrUpdate(ForumComment forumcomment);
	
	public boolean delete(ForumComment forumcomment);
	
	public ForumComment getComment(int id);
	
	public List<ForumComment> list();
}
