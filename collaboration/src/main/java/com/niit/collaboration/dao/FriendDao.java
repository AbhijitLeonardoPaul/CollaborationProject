package com.niit.collaboration.dao;

import java.util.List;


import com.niit.collaboration.model.Friend;

public interface FriendDao {

public boolean save(Friend friend);
	
	public boolean update(Friend friend);
	
	public Friend get(String userId, String friendId);
	
	public List<Friend> getMyFriends(String userId);
	
	public List<Friend> getNewFriendRequests(String friendId);
	
	public void setOnline(String userId);
	
	public void setOffline(String userId);
	
	
	/*public boolean saveOrUpdate(Friend friend);
	
	public boolean delete(Friend friend);
	
	public Friend get(int id);
	
	public List<Friend> list();
	*/
	
}
