(function() {
	'use strict';

	angular.module('app').factory('CheckoutService', CheckoutService);

	CheckoutService.$inject = [ '$http' ];
	function CheckoutService($http) {
		var service = {};

		service.CheckOutItemsFromCart = CheckOutItemsFromCart;

		return service;

		function CheckOutItemsFromCart(usrname) {

			return $http({
				method : 'GET',
				url : ECommerceApp.Constants.CHECKOUTSERVICEURL,
				headers : {
					'username' : usrname
				}
			})
					.then(
							handleSuccess,
							handleError('Error while checking out. Please try again later.'));

			// private functions
			function handleSuccess(data) {
				return data;
			}

			function handleError(error) {
				return function() {
					return {
						success : false,
						message : error
					};
				};
			}
		}
	}

})();