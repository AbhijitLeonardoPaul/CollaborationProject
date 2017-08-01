
var app = angular.module('myApp', ['ngRoute', 'ngCookies', 'ngStorage']);

app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'service/view/home.html',
    controller : 'HomeController as ctrl'
   
  })

  .when('/blog', {
    templateUrl : 'service/view/blog.html',
    controller : 'BlogController as ctrl'
   
  })
  
  .when('/blogs', {
    templateUrl : 'service/view/list_blogs.html',
    controller : 'BlogController as ctrl'
   
  })

  .when('/view_blog/', {
    templateUrl : 'service/view/view_blog.html',
    controller : 'BlogController as ctrl'
    	
  })
  
  
  .when('/list_newblogs/', {
	    templateUrl : 'service/view/list_newblogs.html',
	    controller : 'BlogController as ctrl'
    	
  })
  .when('/forum', {
    		    templateUrl : 'service/view/forum.html',
    		    controller : 'ForumController as ctrl'
    
   
  })
  
   .when('/view_forum/', {
    		    templateUrl : 'service/view/view_forum.html',
    		    controller : 'ForumController as ctrl'
    
   
  })
  .when('/forums/', {
    		    templateUrl : 'service/view/list_forums.html',
    		    controller : 'ForumController as ctrl'
    
   
  })
  
  
  
  
  .when('/friend', {
    templateUrl : 'service/view/friend.html',
    controller : 'FriendController as ctrl'
   
  })
  
 
  
  .when('/view_profile', {
    templateUrl : 'service/view/view_profile.html',
    controller : 'ForumController as ctrl'
   
  })
  
   .when('/job', {
    templateUrl : 'service/view/job.html',
    controller : 'JobController as ctrl'
   
  })
  
  .when('/register', {
    templateUrl : 'service/view/register.html',
    controller : 'UserController as ctrl'
   
  })
  
  


   
  
  
  .when('/jobs', {
    templateUrl : 'service/view/list_jobs.html',
    controller : 'JobController as ctrl'
   
  })
  
     .when('/view_job/', {
    templateUrl : 'service/view/view_job.html',
    controller : 'JobController as ctrl'
    
   
  })
    .when('/vacantjobs', {
    templateUrl : 'service/view/list_vacantjobs.html',
    controller : 'JobController as ctrl'
    
   
  })
  
  

.when('/login', {
    templateUrl : 'service/view/login.html',
    controller : 'UserController as ctrl'
   
  })
  
  .when('/logout', {
    templateUrl : 'service/view/logout.html',
    controller : 'UserController as ctrl'
   
  })
  
  .when('/myprofile', {
    templateUrl : 'service/view/myprofile.html',
    controller : 'UserController as ctrl'
   
  })
  
  
  .otherwise({redirectTo: '/'});
});



app.run(function($rootScope, $location, $cookieStore, $http) {		//run() block gives us facility to inject any instance and constants in our application.
	console.log("--> app : entered app.run");

	$rootScope.$on('$locationChangeStart', function(event, next, current) {		//The $locationChangeStart event can be used to prevent a location change going forward.
		console.log("--> $rootScope.$on <--");
		
		// redirect to login page if try to access any other page rather than the restricted pages
		var restrictedPage = $.inArray($location.path(), [ '/', 
		                                                   '/login', 
		                                                   '/logout', 
		                                                   '/register', 
		                                                   '/list_blog', 
		                                                   '/view_blog', 
		                                                   '/about', 
		                                                   '/list_event',
		                                                   '/view_event', 
		                                                   '/list_forum', 
		                                                   '/view_forum', 
		                                                   '/search_job', 
		                                                   '/view_job_details',
		                                                   '/chat',
		                                                   '/myprofile']) === -1;

		console.log("restrictedPage : " + restrictedPage);
		var loggedIn = $rootScope.currentUser.id;		//taking currentUser.id in $rootScope as 'loggedIn' so that we can use it throughout the session. 

		console.log("loggedIn : " + loggedIn);
		if (restrictedPage && !loggedIn) {
			console.log("Navigating to login page.");
			$location.path('/login');
		}
	});


	// keep user logged in after page refresh...
	/*
	  $rootScope.currentUser = $cookieStore.get('currentUser') || {}; if
	  ($rootScope.currentUser) { $http.defaults.header.common['Authorization'] =
	  'Basic' + $rootScope.currentUser; }*/
	 
});
