'use strict';

app.factory('FriendService', [
		'$http',
		'$q',
		'$rootScope',
		function($http, $q, $rootScope) {
			console.log("FriendService...");

			var BASE_URL = 'http://localhost:8081/Binder'
			return {
				getMyFriends : function() {
					return $http.get(BASE_URL + '/myFriends').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error("-->updateFriendRequest : Error while fetching friends.");
								return $q.reject(errResponse);
							});
				},

				sendFriendRequest : function(friendId) {
					return $http.post(BASE_URL + '/addFriend/' + friendId).then(
							function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error("-->updateFriendRequest : Error while creating friend.")
								return $q.reject(errResponse);
							}
						);
				},
				
				updateFriendRequest : function(friend, id) {
					return $http.put(BASE_URL+'/unFriend/'+friend.id).then(
							function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error("-->updateFriendRequest : Error while updating friend.")
							}
						);
				},
				
				fetchAllUsers : function() {
					return $http.get(BASE_URL+'/users').then(
							function(response) {
								return response.data;
							},
							function(errResponse) {
								console.error('Error while fetching UserDetails...');
								return $q.reject(errResponse);
							}
						);
				}				
			};
		} ]);