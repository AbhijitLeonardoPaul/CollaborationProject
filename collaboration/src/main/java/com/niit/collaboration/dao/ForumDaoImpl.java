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


import com.niit.collaboration.model.Forum;

@EnableTransactionManagement
@Repository(value = "ForumDao")
public class ForumDaoImpl implements ForumDao{

	Logger log = Logger.getLogger(ForumDaoImpl.class);

	@Autowired // @Autowired annotation provides more fine-grained control over
				// where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ForumDaoImpl() {

	}

	public ForumDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	
	
	
	@Transactional
	public boolean save(Forum forum) {
		try {
			log.debug("**********Starting of save() method.");

			forum.setPostDate(new Date(System.currentTimeMillis())); // set
																	// current
																	// time as
	
			// postDate
			forum.setStatus("N");
			Session session = getSession();

			

			session.save(forum);

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
	public boolean update(Forum forum) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(forum);
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
	public boolean saveOrUpdate(Forum forum) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(forum);
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
	public boolean delete(Forum forum) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(forum);
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
	public Forum get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Blog where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (Forum) query.uniqueResult();
		

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Forum> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Blog");
		List<Forum> forumList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return forumList;
	}
}
