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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.niit.collaboration.dao.BlogDao;
import com.niit.collaboration.model.Blog;

@RestController
public class BlogController {
	
	Logger log = Logger.getLogger(BlogController.class);
	
	@Autowired
	BlogDao blogDao;
	
	
	@GetMapping(value = "/blogs")
	public ResponseEntity<List<Blog>> listBlogs() {
		log.debug("**********Starting of listBlogs() method.");
		List<Blog> blog = blogDao.list();
		if(blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listBlogs() method.");
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
	
	@PostMapping(value = "/blog/")
	public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog, HttpSession session) {
		log.debug("**********Starting of saveBlog() method.");
		
		{
			
			/*blog.setUserId("U001");*/
			//blog.setCountLike(0);
			//blog.setStatus("N");
			//blog.setPostDate(new Date());
			blogDao.save(blog);
			log.debug("**********End of saveBlog() method.");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			
		}
		
	}

	@PutMapping(value = "/updateBlog/{id}")   // in URL we give/updateBlog/1
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id, @RequestBody Blog blog) {
		log.debug("**********Starting of updateBlog() method.");
		//if(blogDAO.get(id) == null) 
		{
			//blog = new Blog();
			//blog.setErrorMessage("No blog exist with id : " +blog.getId());
			//log.error("No blog exist with id : " +blog.getId());
			}
		//return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			//else
		{
				Blog blog1=blogDao.get(id);
				blog1.setContent(blog.getContent());
				blogDao.update(blog1);
				log.debug("**********End of updateBlog() method.");
			return new ResponseEntity<Blog>(blog1, HttpStatus.OK);
		}

	}
	
	@DeleteMapping(value = "/deleteBlog/{id}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int id) {
		log.debug("**********Starting of deleteBlog() method.");
		Blog blog = blogDao.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " + id);
			log.error("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDao.delete(blog);
		log.debug("**********End of deleteBlog() method.");
		return new ResponseEntity<Blog>(HttpStatus.OK);		
	}
	
	
	@GetMapping(value = "/getBlog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int id) {
		log.debug("**********Starting of getBlog() method.");
		Blog blog = blogDao.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("No blog exist with id : " + id);
			log.error("No blog exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getBlog() method.");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	/*@PutMapping(value = "/approveBlog/{id}")				
	public ResponseEntity<Blog> approveBlog(@RequestBody Blog blog, @PathVariable("id") int id) {
		log.debug("**********Starting of approveBlog() method.");
		
		blog.setStatus("A");	// A = Accept, R = Reject, N = New
		blogDao.update(blog);
		
		log.debug("**********End of approveBlog() method.");
		return new ResponseEntity<Blog> (blog, HttpStatus.OK);
	}
	@PutMapping(value = "/rejectBlog/{id}")				
	public ResponseEntity<Blog> rejectBlog(@PathVariable("id") int id, @RequestBody Blog blog) {
		log.debug("**********Starting of rejectBlog() method.");
		
		blog.setStatus("R");	// A = Accept, R = Reject, N = New
		blogDao.update(blog);
		
		log.debug("**********End of rejectBlog() method.");
		return new ResponseEntity<Blog> (blog, HttpStatus.OK);
	}
	
	@PutMapping(value = "/likeBlog/{id}")
	public ResponseEntity<Blog> likeBlog(@PathVariable("id") int id, @RequestBody Blog blog){
		log.debug("**********Starting of likeBlog() method.");

		int like = blog.getCountLike();
		blog.setCountLike(like + 1);
		
		blogDao.update(blog);
		
		log.debug("**********End of likeBlog() method.");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}*/
}
