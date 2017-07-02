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

import com.niit.collaboration.model.User;



@EnableTransactionManagement
@Repository(value = "UserDao")
public class UserDaoImpl implements UserDao {

	
	Logger log = Logger.getLogger(UserDaoImpl.class);

	@Autowired // @Autowired annotation provides more fine-grained control over
				// where and how autowiring should be accomplished..
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public UserDaoImpl() {

	}

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected Session getSession() {
		return sessionFactory.openSession();
	}
	
	
	
	

	
	
	public User authenticate(String id, String password) {
		
		String s = "from User where id = "+"'"+id+"'"+" and  password = "+"'"+password+"'";
		
		Session session=getSession();
		
		Query query = session.createQuery(s);
		//set
		/*query.setParameter("id", id);
		query.setParameter("pass", password);*/
		System.out.println(id+" ------- "+password);
		List<User> list = (List<User>) query.list();
		System.out.println("yes"+list.get(0).getId());
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	
public void setOnline(String id) {
		
		String s = "update User set isOnline = 'Y' where id = "+"'"+id+"'";
		Session session=getSession();
		Query query = session.createQuery(s);
	//	query.setParameter(1, id);
		query.executeUpdate();
		
	}
	
	
	@Transactional
	public boolean save(User user) {
		try {
			log.debug("**********Starting of save() method.");

			user.setRole("ROLE_USER");// set
																	// current
																	// time as
	
			// postDate
			user.setStatus("N");
			Session session = getSession();

			

			session.save(user);

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
	public boolean update(User user) {
		try{
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(user);
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
	public boolean saveOrUpdate(User user) {
		try{
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(user);
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
	public boolean delete(User user) {
		try{
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(user);
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
	public User get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Blog where id = ?");
		
		
		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (User) query.uniqueResult();
		

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Blog");
		List<User> userList = query.list();
        session.close();
        log.debug("**********End of list() method.");

		return userList;
	}
}
