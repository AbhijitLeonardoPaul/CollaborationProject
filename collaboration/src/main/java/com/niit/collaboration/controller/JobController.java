package com.niit.collaboration.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.JobDao;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Job;

@RestController
public class JobController {

	
	
Logger log = Logger.getLogger(JobController.class);
	
	@Autowired
	JobDao jobDao;
	
	
	
	@GetMapping(value = "/jobs")
	public ResponseEntity<List<Job>> listJobs() {
		log.debug("**********Starting of listJobs() method.");
		List<Job> job = jobDao.list();
		if(job.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listBlogs() method.");
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/job/")
	public ResponseEntity<Job> saveJob(@RequestBody Job job, HttpSession session) {
		log.debug("**********Starting of saveJob() method.");
		
		{
			
			/*job.setId(100);
			job.setCompanyName("");
			job.setLocation("");
			job.setDescription("");*/
			job.setJobDate(new Date());
			job.setStatus("N");
			jobDao.save(job);
			log.debug("**********End of saveJob() method.");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
			
		}		
	}
	
	@PutMapping(value = "/updateJob/{id}")   // in URL we give/updateJob/1
	public ResponseEntity<Job> updateJob(@PathVariable("id") int id, @RequestBody Job job) {
		log.debug("**********Starting of updateJob() method.");
		//if(blogDAO.get(id) == null) 
		
			
		{
			Job job1=jobDao.get(id);
			job1.setStatus(job.getStatus());
			jobDao.update(job1);
				log.debug("**********End of updateBlog() method.");
			return new ResponseEntity<Job>(job1, HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/deleteJob/{id}")
	public ResponseEntity<Job> deleteJob(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteJob() method.");
		Job job = jobDao.get(id);
		if(job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDao.delete(job);
		log.debug("**********End of deleteJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);		
	}
	@GetMapping(value = "/getJob/{id}")
	public ResponseEntity<Job> getJob(@PathVariable("id") int id) {
		log.debug("**********Starting of getJob() method.");
		Job job = jobDao.get(id);
		if(job == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
}
