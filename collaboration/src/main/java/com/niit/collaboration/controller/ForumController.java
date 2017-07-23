package com.niit.collaboration.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.ForumDao;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.Forum;

@RestController
public class ForumController {
	
	Logger log = Logger.getLogger(ForumController.class);

	@Autowired
	ForumDao forumDao;
	
	
	
	@GetMapping(value = "/forums")
	public ResponseEntity<List<Forum>> listForums() {
		log.debug("**********Starting of listForums() method.");
		List<Forum> forum = forumDao.list();
		if(forum.isEmpty()) {
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listForums() method.");
		return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);
	}
	
	@PostMapping(value = "/forum/")
	public ResponseEntity<Forum> saveForum(@RequestBody Forum forum, HttpSession session) {
		log.debug("**********Starting of saveForum() method.");
		
		{
			
			/*forum.setUserId("U001");*/
			//forum.setCountLike(0);
			//forum.setStatus("N");
			//forum.setPostDate(new Date());
			forumDao.save(forum);
			log.debug("**********End of saveForum() method.");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
			
		}
		
	}
	@DeleteMapping(value = "/deleteForum/{id}")
	public ResponseEntity<Forum> deleteForum(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteForum() method.");
		Forum forum = forumDao.get(id);
		if(forum == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " + id);
			log.error("No forum exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		forumDao.delete(forum);
		log.debug("**********End of deleteForum() method.");
		return new ResponseEntity<Forum>(HttpStatus.OK);		
	}
	
	@GetMapping(value = "/getForum/{id}")
	public ResponseEntity<Forum> getForum(@PathVariable("id") int id) {
		log.debug("**********Starting of getForum() method.");
		Forum forum = forumDao.get(id);
		if(forum == null) {
			forum = new Forum();
			forum.setErrorMessage("No forum exist with id : " + id);
			log.error("No forum exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getForum() method.");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
}
