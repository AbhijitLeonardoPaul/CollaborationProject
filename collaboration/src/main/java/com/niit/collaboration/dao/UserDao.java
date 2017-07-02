package com.niit.collaboration.dao;

import java.util.List;


import com.niit.collaboration.model.User;

public interface UserDao {

	
public boolean save(User user);
	
	public boolean update(User user);
	
	public boolean saveOrUpdate(User user);
	
	public boolean delete(User user);
	
	public User get(int id);
	
	public List<User> list();
	
	public User authenticate(String id, String password);
	
	public void setOnline(String id);
}
