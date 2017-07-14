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
			
			self.sendFriendRequest = sendFriendRequest
			
			function sendFriendRequest(friendId) {
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
			
			self.getMyFriends = function() {
				console.log("--> getMyFriends");
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
			
			self.fetchAllUsers = function() {
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