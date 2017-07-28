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
							.get(BASE_URL+'/getBlog/'+id)
							.then(function(response) {
								$rootScope.selectedBlog = response.data;
								return response.data;
							},
							function(errResponse) {
								console.error('Error while getting blog details');
								return $q.reject(errResponse);
							});
			},
			
			updateBlog : function(blog, id) {
				console.log("--> BlogService : calling 'updateBlog' method with id : "+id);
				return $http
							.put(BASE_URL+'/updateBlog/'+id)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while updating Blog');
								return $q.reject(errResponse);
							});
			},
			
			deleteBlog : function(id) {
				console.log("--> BlogService : calling 'deleteBlog' method with id : "+id);
				return $http
							 delete(BASE_URL+'/deleteBlog/'+id)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.log('Error while deleting Blog');
								return $q.reject(errResponse);
							});
			},
			
			approveBlog : function(blog, id) {
				console.log("-->BlogService : calling approveBlog() method : getting blog with id : " + id);
				return $http
							.put(BASE_URL+'/approveBlog/'+ id, blog)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.log("Error while approving Blog");
								return $q.reject(errResponse);
							});
			},
			
			rejectBlog : function(blog, id) {
				console.log("-->BlogService : calling rejectBlog() method : getting blog with id : " + id);
				return $http
							.put(BASE_URL+'/rejectBlog/'+ id, blog)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.log("Error while rejecting Blog");
								return $q.reject(errResponse);
							});
			},
			
			likeBlog : function(blog, id) {
				console.log("-->BlogService : calling likeBlog() method : getting blog with id : " + id);
				return $http
							.put(BASE_URL+'/likeBlog/'+id, blog)
							.then(function(response) {
								return response.data;
							},
							function(errResponse) {
								console.log("Error while liking Blog.");
								return $q.reject(errResponse);
							});
			}
			
		};

}]);