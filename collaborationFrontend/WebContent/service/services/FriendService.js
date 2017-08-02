'use strict';

app
		.factory(
				'FriendService',
				[
						'$http',
						'$q',
						'$rootScope',
						function($http, $q, $rootScope) {
							console.log("FriendService...");

							var BASE_URL = 'http://localhost:9999/collaboration'
							return {
								getMyFriends : function() {
									return $http
											.get(BASE_URL + '/myFriends')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error("-->updateFriendRequest : Error while fetching friends.");
														return $q
																.reject(errResponse);
													});
								},
								
								
								unFriend : function(friend, id) {
									console
											.log("--> FriendService : calling 'unFriend' method with id : "
													+ id);
									return $http
											.put(
													BASE_URL
															+ '/user/unFriend/'
															+ id, friend)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error("-->FriendService : Error while unFriending existing friend.")
													});
								},
								/*sendFriendRequest : function(friendId) {
									return $http
											.post(
													BASE_URL + '/addFriend/'
															+ friendId)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error("-->updateFriendRequest : Error while creating friend.")
														return $q
																.reject(errResponse);
													});
								},*/
								
								rejectFriend : function(friend, id) {
									console.log("--> FriendService : calling 'rejectFriend' method with id : "+id);
									return $http.put(BASE_URL+'/user/rejectFriend/'+id, friend)
									.then(function(response) {
												return response.data;
											},
											function(errResponse) {
												console.error("-->FriendService : Error while rejecting friend request.")
											});
								},
								

								acceptFriend : function(friend, id) {
									console.log("--> FriendService : calling 'acceptFriend' method with id : "+id);
									return $http.put(BASE_URL+'/user/acceptFriend/'+id, friend)
									.then(function(response) {
												return response.data;
											},
											function(errResponse) {
												console.error("-->FriendService : Error while accepting friend request.")
											});
								},

								updateFriendRequest : function(friend, id) {
									return $http
											.put(
													BASE_URL + '/unFriend/'
															+ friend.id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error("-->updateFriendRequest : Error while updating friend.")
													});
								},

								fetchAllUsers : function() {
									return $http
											.get(BASE_URL + '/users')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching UserDetails...');
														return $q
																.reject(errResponse);
													});
								}
							};
						} ]);