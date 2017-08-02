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

import com.niit.collaboration.dao.UserDao;

import com.niit.collaboration.model.User;

@RestController
public class UserController {

Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	UserDao userDao;
	
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> listUsers() {
		log.debug("**********Starting of listJobs() method.");
		List<User> user = userDao.list();
		if(user.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listUsers() method.");
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}
	
	@PostMapping(value = "/user/")
	public ResponseEntity<User> saveUser(@RequestBody User user, HttpSession session) {
		log.debug("**********Starting of saveUser() method.");
		
		{
			
			/*user.setId("u100");
			user.setEmail("");
			user.setName("");
			user.setRole("");
			user.setPassword("");*/
			user.setStatus("N");
			user.setIsOnline("isOnline");
			userDao.save(user);
			log.debug("**********End of saveJob() method.");
			return new ResponseEntity<User>(user, HttpStatus.OK);
			
		}		
	}
	
	
	

	@PutMapping(value = "/updateUser/{id}")   // in URL we give/updateUser/1
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		log.debug("**********Starting of updateUser() method.");
		//if(blogDAO.get(id) == null) 
		
			
		{
			User user1=userDao.get(id);
			user1.setStatus(user1.getStatus());
			userDao.update(user1);
				log.debug("**********End of updateUser() method.");
			return new ResponseEntity<User>(user1, HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		log.debug("**********Starting of deleteUser() method.");
		User user = userDao.get(id);
		if(user == null) {
			user = new User();
			user.setErrorMessage("No job exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		userDao.delete(user);
		log.debug("**********End of deleteUser() method.");
		return new ResponseEntity<User>(HttpStatus.OK);		
	}
	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		log.debug("**********Starting of getUser() method.");
		User user = userDao.get(id);
		if(user == null) {
			user = new User();
			user.setErrorMessage("No user exist with id : " + id);
			log.error("No job exist with id : " + id);
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getUser() method.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/user/login")
	public ResponseEntity<User> login(@RequestBody User users, HttpSession session) {
		log.debug("**********Starting of login() method.");
		System.out.println(users.getId()+"  "+users.getPassword());
		users = userDao.authenticate(users.getId(), users.getPassword());
		if(users == null) {
			users = new User();	//we need to create new users object to set errorMsg and errorCode...
			users.setErrorCode("404");
			users.setErrorMessage("Invalid userId or password...");
			log.error("Invalid userId or password...");
		}
		else {
			session.setAttribute("loggedInUser", users);
			session.setAttribute("loggedInUserID", users.getId());
			session.setAttribute("LoggedInStatus", users.getIsOnline());
			
			/*friendDAO.setOnline(users.getId());*/
			userDao.setOnline(users.getId());
		}
		log.debug("**********End of login() method.");
		return new ResponseEntity<User>(users, HttpStatus.OK);
	}
}

