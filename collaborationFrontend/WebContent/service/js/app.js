
var app = angular.module('myApp2', ['ngRoute', 'ngCookies' ]);

app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'service/view/home.html',
    controller : 'HomeController as ctrl'
   
  })

  .when('/blog', {
    templateUrl : 'service/view/blog.html',
    controller : 'BlogController'
   
  })

  .when('/friend', {
    templateUrl : 'service/view/friend.html',
    controller : 'FriendController'
   
  })
  
   .when('/job', {
    templateUrl : 'service/view/job.html',
    controller : 'JobController as ctrl'
   
  })
  
  .when('/register', {
    templateUrl : 'service/view/register.html',
    controller : 'UserController as ctrl'
   
  })
  
  


   .when('/login', {
    templateUrl : 'service/view/login.html',
    controller : 'UserController as ctrl'
   
  })


  .otherwise({redirectTo: '/'});
});

