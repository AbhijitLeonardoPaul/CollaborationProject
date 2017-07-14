'use strict';

app.factory('BlogService', ['$http', '$q', '$rootScope',
    function($http, $q, $rootScope) {
	console.log("BlogService...");

		var BASE_URL='http://localhost:9999/collaboration'
			return {

			listBlogs : function() {
				console.log("-->BlogService : calling 'listBlogs' method.");
				return $http
							.get(BASE_URL+'/blogs')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting Blog list...');
								return $q.reject(errResponse);
							});
			},
			
			createBlog : function(blog) {
				console.log("-->BlogService : calling 'createBlog' method.");
				return $http
							.post(BASE_URL+'/blog/', blog)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while posting new Blog...');
								return $q.reject(errResponse);
							});
			},
			
			updateBlog : function(blog, id) {
				console.log("-->BlogService : calling 'updateBlog' method.");
				return $http
							.put(BASE_URL+'/blog/'+id)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error("Error while updating blog.");
								return $q.reject(errResponse);
							});
			},

			getBlog : function(id) {
				console.log("-->BlogService : calling 'getBlog' method with id : "+id);
				return $http
							.get(BASE_URL+'/blog/'+id)
							.then(function(response) {
								$rootScope.selectedBlog = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting blog details');
								return $q.reject(errResponse);
							});
			},
			
			listBlogApplications : function() {
				console.log("-->BlogService : calling 'listBlogApplications' method");
				return $http
							.get(BASE_URL+'/blogApplications')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting BlogApplication list...');
								return $q.reject(errResponse);
							});
			},

			getMyAppliedBlogs : function() {
				console.log("-->BlogService : calling 'getMyAppliedBlogs' method");
				return $http
							.get(BASE_URL+'/getMyAppliedBlogs')
							.then(function(response) {
								$rootScope.getAppliedBlog = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting all applied blogs...');
								return $q.reject(errResponse);
							});
			},

			callForInterview : function(blogApplication, userId, blogId) {
				console.log("-->BlogService : calling 'callForInterview' method with userId : "+userId+" blogId : "+blogId);
				return $http
							.put(BASE_URL+'/callForInterview/'+userId+'/'+blogId)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while updating Blog Application...');
								return $q.reject(errResponse);
							});
			},

			rejectBlogApplication : function(blogApplication, userId, blogId) {
				console.log("-->BlogService : calling 'rejectBlogApplication' method with userId : "+userId+" blogId : "+blogId);
				return $http
							.put(BASE_URL+'/rejectBlogApplication/'+userId+'/'+blogId)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while updating Blog Application...');
								return $q.reject(errResponse);
							});
			},
			
			listVacantBlogs : function() {
				console.log("-->BlogService : calling 'listVacantBlogs' method.");
				return $http
							.get(BASE_URL+'/listVacantBlogs')
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error("Error while getting all vacant blogs.");
								return $q.reject(errResponse);
							});
			},

			applyForBlog : function(blog) {
				console.log("-->BlogService : calling 'applyForBlog' method.", self.blog);
				return $http
							.post(BASE_URL+'/blogApplied', blog)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while applying for Blog...');
								return $q.reject(errResponse);
							});
			}
		};
}]);