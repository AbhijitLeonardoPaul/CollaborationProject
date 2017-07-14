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
