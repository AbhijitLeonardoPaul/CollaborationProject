'use strict';

app.controller('FriendController', [ 'FriendService', 'UserService', '$scope',
		'$location', '$rootScope',
		function(FriendService, UserService, $scope, $location, $rootScope) {
			console.log("FriendController...");

			var self = this;
			self.friend = {
				id : '',
				userId : '',
				friendId : '',
				isOnline : '',
				status : ''
			};
			self.friends = [];

			self.user = {
					errorCode: '',
					errorMessage: '',
					id: '',
					name: '',
					password: '',
					email: '',
					role: '',
					status: '',
					isOnline: '',
			};
			self.users = [];
			
			self.sendFriendRequest = function sendFriendRequest(friendId) {
				console.log("--> sendFriendRequest : "+friendId);
				FriendService.sendFriendRequest(friendId).then(
						function(d) {
							self.friend = d;
							alert("Friend request sent...")
						},
						function(errResponse) {
							console.error("Error while fetching friends.");
						}
					);
			};
			
			
			
			self.rejectFriend = function(friend, id) {
				console.log("--> FriendController : calling 'rejectFriend' method with id : "+id);
				console.log("--> FriendController",self.friend);
				FriendService
								.rejectFriend(friend, id)
								.then(function(d) {
									self.friend = d;
									alert('Friend request rejected successfully...');
									self.getNewFriendRequests();
								},
								function(errResponse) {
									console.error("Error while updating friend.");
								});
			};
			
			self.getMyFriends = function(d) {
				console.log("--> getMyFriends");
				var currentUser = $rootScope.currentUser
				if (typeof currentUser == 'undefined') {
					alert("Please Sign in to check Friend List...")
					console.log('User not logged in , to check Friend List...');
					$location.path('/login');
				};
				FriendService.getMyFriends().then(
						function(d) {
							self.friends = d;
							console.log("Got the Friendlist.");
						},
						function(errResponse) {
							console.error("Error while fetching Friends.");
						}
					);
			};
			
			self.updateFriendRequest = function(friend, id) {
				console.log("--> updateFriendRequest");
				FriendService.updateFriendRequest(friend.id).then(self.fetchAllFriends,
						function(errResponse) {
							console.error("Error while updating friend.");
						}
					);
			};
			
			
			self.acceptFriend = function(friend, id) {
				console.log("--> FriendController : calling 'acceptFriend' method with id : "+id);
				console.log("--> FriendController",self.friend);
				FriendService
								.acceptFriend(friend, id)
								.then(function(d) {
									self.friend = d;
									alert('Friend request accepted successfully...');
									self.getNewFriendRequests();
								},
								function(errResponse) {
									console.error("Error while updating friend.");
								});
			};
			
			self.unFriend = function(friend, id) {
				console.log("--> FriendController : calling 'unFriend' method with id : "+id);
				console.log("--> FriendController",self.friend);
				FriendService
								.unFriend(friend, id)
								.then(function(d) {
									self.friend = d;
									self.getMyFriends();
								},
								function(errResponse) {
									console.error("Error while updating friend.");
								});
			};			
			self.fetchAllUsers = function(d) {
				console.log("--> fetchAllUsers");
				UserService.fetchAllUsers().then(
						function() {
							self.users = d;
						},
						function(errResponse) {
							console.error("Error while fetching users.");
						}
					);
			};
			
			
			self.fetchAllUsers();
			self.getMyFriends();
			
		} ]);