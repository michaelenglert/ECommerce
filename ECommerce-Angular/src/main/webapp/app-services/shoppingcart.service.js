(function() {
	'use strict';

	angular.module('app').factory('CartService', CartService);

	CartService.$inject = [ '$http' ];
	function CartService($http) {
		var service = {};

		service.getCartItems = getCartItems;
		service.addItemToCart = addItemToCart;
		service.removeItemFromCart = removeItemFromCart;

		return service;

		function getCartItems(usrname) {

			return $http(
					{
						method : 'GET',
						url : ECommerceApp.Constants.CARTSERVICEGETITEMSURL,
						headers: {
							   'username': usrname
							 }
					}).then(handleSuccess,
					handleError('Error while loading Cart Items. Please try again later.'));

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
		
		function addItemToCart(usrname,id) {

			return $http(
					{
						method : 'GET',
						url : ECommerceApp.Constants.CARTSERVICEADDITEMSURL + "/" + id,
						headers: {
							   'username': usrname
							 }
					}).then(handleSuccess,
					handleError('Error in adding Items to Cart. Please try again later.'));

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
		
		function removeItemFromCart(usrname,id) {

			return $http(
					{
						method : 'DELETE',
						url : ECommerceApp.Constants.CARTSERVICEREMOVEITEMSURL  + "/" + id,
						headers: {
							   'username': usrname
							 }
					}).then(handleSuccess,
					handleError('Error in removing Items from Cart. Please try again later.'));

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