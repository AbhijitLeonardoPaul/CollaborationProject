package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Job;

public interface JobDao {

public boolean save(Job job);
	
	public boolean update(Job job);
	
	public boolean saveOrUpdate(Job job);
	
	public boolean delete(Job job);
	
	public Job get(int id);
	
	public List<Job> list();
}
