(function() {
	'use strict';

	angular.module('app').controller('LoginController',LoginController);

	LoginController.$inject = [ '$location', 'AuthenticationService','FlashService','$rootScope' ];
	function LoginController($location, AuthenticationService, FlashService,$rootScope) {
		
		var lc = this;
		lc.login = login;

		(function initController() {
			// reset login status
			AuthenticationService.ClearCredentials();
			$rootScope.flash = null;
			$("#licartsymbol").hide();
			$("#liuser").hide();
			$("#lihome").hide();
		})();

		function login() {
			lc.dataLoading = true;
			AuthenticationService.Login(lc.username, lc.password, function(response) {
				if (response.success) {
					AuthenticationService.SetCredentials(lc.username,lc.password);
					$location.path('/');
				} else {
					FlashService.Error(response.message);
					lc.dataLoading = false;
				}
			});
		};
	}

})();