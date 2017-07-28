'use strict'

app.controller('BlogController', ['BlogService', '$scope', '$location', '$rootScope',
	function(BlogService, $scope, $location, $rootScope) {
		console.log('BlogController...');
		
		
		var self = this;
	self.blog = {
		id : '', 
		title : '', 
		content : '', 
		postDate : '',
		userId : '', 
		status : '', 
		countLike : '',
		errorCode : '',
		errorMessage : ''
	};

self.blogs = [];
	self.listblogs = function() {
		console.log("-->BlogController : calling 'listBlogs' method.");
		BlogService
					.listBlogs()
					.then(function(d) {
						self.blogs = d;
					},
					function(errResponse) {
						console.error("Error while getting blog list.")
					});
	};		
	self.listblogs();
	
	self.createBlog = function(blog) {
		console.log("-->BlogController : calling 'createBlog' method.");
		blog.userId=$rootScope.currentUser.id;
		BlogService
					.createBlog(blog)
					.then(function(d) {
						self.blog = d;
						alert('Blog posted successfully...')
					},
					function(errResponse) {
						console.error('Error while posting new Blog...');
					});
	};
	
	
	self.getBlog = function(id) {
		
		console.log("-->BlogController : calling 'getBlog' method with id : "+id);
		BlogService
					.getBlog(id)
					.then(function(d) {
						self.blog = d;
						
						$location.path('/view_blog/');
					},
					function(errResponse) {
						console.error('Error while fetching blog details...')
					});
	};
	
	self.approveBlog = function(blog, id) {
		console.log("-->BlogController : calling approveBlog() method : Blog id is : " + id);
		console.log("-->BlogController",self.blog);
		BlogService.approveBlog(blog, id).then(
				self.listblogs,
				function(errResponse) {
					console.error("Error while approving blog...")
				});
	};

	self.rejectBlog = function(blog, id) {
		console.log("-->BlogController : calling rejectBlog() method : Blog id is : " + id);
		console.log("-->BlogController",self.blog);
		BlogService.rejectBlog(blog, id).then(
				self.listblogs,
				function(errResponse) {
					console.error("Error while rejecting blog...")
				});
	};
	
	self.likeBlog = function(blog, id) {
		console.log("-->BlogController : calling likeBlog() method : Blog id is : "+id);
		console.log("-->BlogController", self.blog);
		BlogService.likeBlog(blog, id).then(
				self.listblogs,
				function(errResponse) {
					console.error("Error while liking the blog...")
				});
		
	};

	
	
	
	self.submit = function() {
		{
			console.log("-->BlogController : calling 'submit()' method.", self.blog);
			self.createBlog(self.blog);
			console.log('Saving new Blog', self.blog);
		}
		self.reset();
	};
	
	self.reset = function() {
		console.log('submit a new blog', self.blog);
		self.blog = {
				id : '', 
				title : '', 
				content : '', 
				postDate : '',
				userId : '', 
				status : '', 
				countLike : '',
				errorCode : '',
				errorMessage : ''
			};
		$scope.myForm.$setPristine();	//reset form...
		};
	
	
} ]);
