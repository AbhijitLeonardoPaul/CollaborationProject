package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Forum;

public interface ForumDao {

	
public boolean save(Forum forum);
	
	public boolean update(Forum forum);
	
	/*public boolean saveOrUpdate(Forum forum);*/
	
	public boolean delete(Forum forum);
	
	public Forum get(int id);
	
	public List<Forum> list();
}
