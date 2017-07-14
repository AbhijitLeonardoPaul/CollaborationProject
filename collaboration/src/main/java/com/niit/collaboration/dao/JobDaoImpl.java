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


import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;

@EnableTransactionManagement
@Repository(value = "jobDao")
public class JobDaoImpl implements JobDao {

	Logger log = Logger.getLogger(JobDaoImpl.class);

	/*@Autowired
	Job job;*/
	@Autowired // @Autowired annotation provides more fine-grained control over
				// where and how autowiring should be accomplished..
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
	 *	Constructor of JobDAOImpl... 
	 */
	public JobDaoImpl() {

	}

	public JobDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}
	/**
	 *  Declare all CRUD Operations...
	 */
	@Transactional
	public boolean save(Job job) {
		try {
			log.debug("**********Starting of save() method.");
			job.setStatus("V");	//V-Vacant	F-Filled	P-Pending
			job.setJobDate(new Date(System.currentTimeMillis())); // set
			job.setNoOfApplicants(0);														// current
																	// time as

			// postDate
			
			Session session = getSession();

			session.save(job);

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
	public boolean update(Job job) {
		try {
			log.debug("**********Starting of update() method.");

			Session session = getSession();
			session.update(job);
			session.flush();
			session.close();

			log.debug("**********End of update() method.");

			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());

			e.printStackTrace();
			return false;
		}
	}

	/*@Transactional
	public boolean saveOrUpdate(Job job) {
		try {
			log.debug("**********Starting of saveOrUpdate() method.");

			Session session = getSession();
			session.saveOrUpdate(job);
			session.flush();
			session.close();
			log.debug("**********End of saveOrUpdate() method.");

			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}*/

	@Transactional
	public boolean delete(Job job) {
		try {
			log.debug("**********Starting of delete() method.");

			Session session = getSession();
			session.delete(job);
			session.flush();
			session.close();
			log.debug("**********End of delete() method.");

			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());

			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Job get(int id) {
		log.debug("**********Starting of get() method.");

		Session session = getSession();

		Query query = session.createQuery("from Job where id = ?");

		query.setInteger(0, id);
		log.debug("**********End of get() method.");
		return (Job) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> list() {
		log.debug("**********Starting of list() method.");

		Session session = getSession();

		Query query = session.createQuery("from Job");
		List<Job> jobList = query.list();
		session.close();
		log.debug("**********End of list() method.");

		return jobList;
	}

@Transactional
public List<Job> listVacantJobs() {
	log.debug("**********Starting of listVacantJobs() method.");
	String hql = "from Job where status = 'V'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	log.debug("**********End of listVacantJobs() method.");
	return query.list();
}
@Transactional
public List<JobApplication> listJobApplications() {
	log.debug("**********Starting of listJobApplications() method.");
	String hql = "from JobApplication";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	log.debug("**********End of listJobApplications() method.");
	return query.list();
}
@Transactional
public boolean applyForJob(JobApplication jobApplication) {
	try {
		log.debug("**********Starting of applyForJob() method.");
		sessionFactory.getCurrentSession().save(jobApplication);
		log.debug("**********End of applyForJob() method.");
		return true;
	} catch (Exception e) {
		log.error("Error occured : " + e.getMessage());
		e.printStackTrace();
		return false;
	}
}
@Transactional
public boolean updateJobApplication(JobApplication jobApplication) {
	try {
		log.debug("**********Starting of updateJobApplication() method.");
		sessionFactory.getCurrentSession().update(jobApplication);
		log.debug("**********End of updateJobApplication() method.");
		return true;
	} catch (Exception e) {
		log.error("Error occured : " + e.getMessage());
		e.printStackTrace();
		return false;
	}
}
@Transactional
public JobApplication get(String userid, String jobid) {
	log.debug("**********Starting of get() method.");
	String hql = "from JobApplication where userid = '" + userid + "' and jobid = '" + jobid + "'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	log.debug("**********End of get() method.");
	return (JobApplication) query.list();
}

@Transactional
public List<Job> getMyAppliedJobs(String userid) {
	log.debug("**********Starting of getMyAppliedJobs() method.");
	String hql = "from Job where id in (select jobid from JobApplication where userid = '" + userid + "')";
	log.debug("******hql query : "+hql);
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	log.debug("**********End of getMyAppliedJobs() method.");
	return query.list();
}
}

