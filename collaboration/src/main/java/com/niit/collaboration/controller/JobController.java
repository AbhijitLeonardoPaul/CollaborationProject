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
import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.User;

@RestController
public class JobController {

	
	
Logger log = Logger.getLogger(JobController.class);
	
	@Autowired
	JobDao jobDao;
	/*@Autowired
	JobApplication jobApplication;*/
	/*@Autowired
	Job job;
	*/
	
	/**
	 * http://localhost:8081/Binder/jobs 					//working
	 * 
	 * @return
	 */
	@GetMapping(value = "/jobs")
	public ResponseEntity<List<Job>> listJobs() {
		log.debug("**********Starting of listJobs() method.");
		List<Job> job = jobDao.list();
		if(job.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listJobs() method.");
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
	}
	
	@GetMapping(value = "/jobApplications")
	public ResponseEntity<List<JobApplication>> listJobApplications() {
		log.debug("**********Starting of listJobApplications() method.");
		
		List<JobApplication> jobApplications = jobDao.listJobApplications();
		
		log.debug("**********End of listJobApplications() method.");
		return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
	}
	
	
	/**
	 * http://localhost:8081/Binder/job/ 					//working
	 * 
	 * @param job
	 * @return
	 */
	@PostMapping(value = "/job/")
	public ResponseEntity<Job> saveJob(@RequestBody Job job, HttpSession session) {
		log.debug("**********Starting of saveJob() method.");
		
		{
			
			/*job.setId(100);
			job.setCompanyName("");
			job.setLocation("");
			job.setDescription("");*/
			job.setJobDate(new Date());
			job.setStatus("v");
			jobDao.save(job);
			log.debug("**********End of saveJob() method.");
			return new ResponseEntity<Job>(job, HttpStatus.OK);
			
		}		
	}
	/**
	 * http://localhost:8081/Binder/job/{id} 					//working
	 * 
	 * @param id
	 * @param job
	 * @return
	 */
	
	@PutMapping(value = "/updateJob/{id}")   // in URL we give/updateJob/1
	public ResponseEntity<Job> updateJob(@PathVariable("id") int id, @RequestBody Job job) {
		log.debug("**********Starting of updateJob() method.");
		if (jobDao.get(id) == null) {
			job = new Job();
			job.setErrorMessage("No job exist with id : " + job.getId());
			log.error("No job exist with id : " + job.getId());
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDao.update(job);
		log.debug("**********End of updateJob() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
		/*{
			Job job1=jobDao.get(id);
			job1.setStatus(job.getStatus());
			jobDao.update(job1);
				log.debug("**********End of updateBlog() method.");
			return new ResponseEntity<Job>(job1, HttpStatus.OK);
		}*/
	
	/**
	 * http://localhost:8081/Binder/job/{id} 					//working
	 * 
	 * @param id
	 * @return
	 */
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
	
	/**
	 * http://localhost:8081/Binder/getMyAppliedJobs
	 * 
	 * @param httpSession
	 * @return
	 */
	@GetMapping(value = "/getMyAppliedJobs")
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		log.debug("**********Starting of getMyAppliedJobs() method.");
		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		String loggedInUserId = loggedInUser.getId();
		
		@SuppressWarnings("unchecked")
		List<Job> jobs = (List<Job>) jobDao.getMyAppliedJobs(loggedInUserId);
		log.debug("**********End of getMyAppliedJobs() method.");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/callForInterview/{userId}/{jobId}
	 * @param userId
	 * @param jobId
	 * @param jobApplication
	 * @return
	 */
	@PutMapping(value = "/callForInterview/{userId}/{jobId}")
	public ResponseEntity<Job> callForInterview(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		log.debug("**********Starting of callForInterview() method.");
		jobApplication = jobDao.get(userId, jobId);
		jobApplication.setStatus("C");
		Job job = new Job();
		if (jobDao.updateJobApplication(jobApplication) == false) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to change job application status 'call for interview'...");
			log.error("Not able to change job application status 'call for interview'...");
		}
		log.debug("**********End of callForInterview() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	/**
	 * http://localhost:8081/Binder/rejectJobApplication/{userId}/{jobId}
	 * @param userId
	 * @param jobId
	 * @param jobApplication
	 * @return
	 */
	
	@PutMapping(value = "/rejectJobApplication/{userId}/{jobId}")
	public ResponseEntity<Job> rejectJobApplication(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		log.debug("**********Starting of rejectJobApplication() method.");
		jobApplication = jobDao.get(userId, jobId);
		jobApplication.setStatus("R");
		Job job = new Job();
		if (jobDao.updateJobApplication(jobApplication) == false) {
			
			job.setErrorCode("404");
			job.setErrorMessage("Not able to reject the application...");
			log.error("Not able to reject the application...");
		}
		log.debug("**********End of rejectJobApplication() method.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
		
	}
	/**
	 * http://localhost:8081/Binder/listVacantJobs //working
	 * 
	 * @return
	 */
	@GetMapping(value = "/listVacantJobs")
	public ResponseEntity<List<Job>> listVacantJobs() {
		log.debug("**********Starting of listVacantJobs() method.");
		List<Job> vacantJobs = jobDao.listVacantJobs();
		if (vacantJobs.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listVacantJobs() method.");
		return new ResponseEntity<List<Job>>(vacantJobs, HttpStatus.OK);
	}

	/**
	 * http://localhost:8081/Binder/jobApplied
	 * 
	 * @param jobApplication
	 * @param httpSession
	 * @return
	 */
	@PostMapping(value = "/jobApplied")
	public ResponseEntity<Job> applyForJob(@RequestBody Job job, HttpSession httpSession) {		
		log.debug("**********Starting of applyForJob() method.");
		JobApplication jobApplication = new JobApplication();
		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		jobApplication.setUserid(loggedInUser.getId());
		jobApplication.setJobid(job.getId());
		jobApplication.setStatus("A"); // A = Applied ||R = Rejected ||C = Call for Interview 

		jobDao.applyForJob(jobApplication);

		log.debug("**********End of applyForJob() method.");
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
}

