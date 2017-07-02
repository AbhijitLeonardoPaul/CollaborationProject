package com.niit.collaboration.dao;

import java.util.List;


import com.niit.collaboration.model.BlogComment;

public interface BlogCommentDao {

public boolean save(BlogComment blogcomment);
	
	public boolean update(BlogComment blogcomment);
	
	public boolean saveOrUpdate(BlogComment blogcomment);
	
	public boolean delete(BlogComment blogcomment);
	
	public BlogComment get(int id);
	
	public List<BlogComment> list();

}
