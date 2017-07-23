package com.niit.collaboration.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


import com.niit.collaboration.model.Friend;




@EnableTransactionManagement
@Repository(value="friendDAO")
public class FriendDaoImpl implements FriendDao {
	
	Logger log = Logger.getLogger(FriendDaoImpl.class);
	
	@Autowired	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	/**
	 *  getter/setter method for sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *   Constructor of FriendDAOImpl...
	 */
	public FriendDaoImpl() { 		
		
	}	
	public FriendDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	
	@Transactional
	public boolean save(Friend friend){
		try {
			log.debug("**********Starting of save() method.");
			sessionFactory.getCurrentSession().save(friend);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Friend friend){
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(friend);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Friend get(String userId, String friendId) {
		log.debug("**********Starting of get() method.");
		String hql = "from Friend where userId = '" + userId + "' and friendId = '" + friendId + "'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		log.debug("**********End of get() method.");
		return null;
	}
	
	@Transactional
	public List<Friend> getMyFriends(String userId) {
		log.debug("**********Starting of getMyFriends() method.");
		String hql = "from Friend where userId = '" + userId + "' and status = 'Y'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		log.debug("**********End of getMyFriends() method.");
		return list;
	}
	
	@Transactional
	public List<Friend> getNewFriendRequests(String friendId) {
		log.debug("**********Starting of getNewFriendRequests() method.");
		String hql = "from Friend where userId = '" + friendId + "' and status = 'N'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = (List<Friend>) query.list();
		log.debug("**********End of getNewFriendRequests() method.");
		return list;
	}
	
	@Transactional
	public void setOnline(String userId) {
		log.debug("**********Starting of setOnline() method.");
		String hql = "update Friend set isOnline = 'Y' where userId = '" + userId + "'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("**********End of setOnline() method.");
	}
	
	@Transactional
	public void setOffline(String userId) {
		log.debug("**********Starting of setOffline() method.");
		String hql = "update Friend set isOnline = 'N' where userId = '" + userId + "'";
		log.debug("hql : " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("**********End of setOffline() method.");
	}		
}

/*@EnableTransactionManagement
@Repository(value = "FriendDao")
public class FriendDaoImpl implements FriendDao {

	
	Logger log = Logger.getLogger(FriendDaoImpl.class);

	@Autowired // @Autowired annotation provides more fine-grained control over
				// where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public FriendDaoImpl() {

	}

	public FriendDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	
	
	
	@Transactional
	public boolean save(Friend friend) {
		try {
			log.debug("**********Starting of save() method.");

			friend.setPostDate(new Date(System.currentTimeMillis())); // set
																	// current
																	// time as
	
			// postDate
			friend.setStatus("N");
			Session session = getSession();

			

			session.save(friend);

			session.flush();

			session.close();
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Friend friend) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(friend);
			session.flush();
			session.close();
			
			
			log.debug("**********End of update() method.");

			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());

				e.printStackTrace();
				return false;
			}
	}
	
	@Transactional
	public boolean saveOrUpdate(Friend friend) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(friend);
			session.flush();
			session.close();
			log.debug("**********End of saveOrUpdate() method.");

			return true;
			}catch(Exception e){
				log.error("Error occured : " + e.getMessage());
				e.printStackTrace();
				return false;
			}
	}
	
	@Transactional
	public boolean delete(Friend friend) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(friend);
			session.flush();
			session.close();
			log.debug("**********End of delete() method.");

			return true;
		}catch (Exception e){
			log.error("Error occured : " + e.getMessage());

			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Friend get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Blog where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (Friend) query.uniqueResult();
		

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Friend> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Blog");
		List<Friend> friendList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return friendList;
	}
}
*/