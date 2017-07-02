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

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;

@EnableTransactionManagement
@Repository(value = "blogCommentDao")
public class BlogCommentDaoImpl implements BlogCommentDao {

	
	Logger log = Logger.getLogger(BlogCommentDaoImpl.class);

	@Autowired // @Autowired annotation provides more fine-grained control over
				// where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public BlogCommentDaoImpl() {

	}

	public BlogCommentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	@Transactional
	public boolean save(BlogComment blogcomment) {
		try {
			log.debug("**********Starting of save() method.");

			blogcomment.setCommentDate(new Date(System.currentTimeMillis())); // set
																	// current
																	// time as
	
			// postDate
			/*blogcomment.setStatus("N");*/
			Session session = getSession();

			

			session.save(blogcomment);

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
	public boolean update(BlogComment blogcomment) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(blogcomment);
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
	public boolean saveOrUpdate(BlogComment blogcomment) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(blogcomment);
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
	public boolean delete(BlogComment blogcomment) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(blogcomment);
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
	public BlogComment get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from BlogComment where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (BlogComment) query.uniqueResult();
		

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<BlogComment> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from BlogComment");
		List<BlogComment> blogCommentList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return blogCommentList;
	}
}
