(function() {
	'use strict';

	angular.module('app').factory('UserService', UserService);

	UserService.$inject = [ '$http' ];
	function UserService($http) {
		var service = {};   

		service.ValidateUser = ValidateUser;

		return service;

		function ValidateUser(usr, pwd) {
			
			// get the form data
			var formData = {
				'username' : usr,
				'password' : pwd
			};

			return $http({
				method : 'POST',
				url : ECommerceApp.Constants.USERSERVICEURL,
				data : $.param(formData), // pass in data as strings
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			// set the headers so angular passing info as form data (not request
			// payload)
			}).then(handleSuccess, handleError('Error while logging in. Please try again later.'));
			
			// private functions

	        function handleSuccess(data) {
	            return data;
	        }

	        function handleError(error) {
	            return function () {
	                return { success: false, message: error };
	            };
	        }
		}
	}

})();