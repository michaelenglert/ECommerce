(function() {
	'use strict';

	angular.module('app').controller('ShoppingCartController', ShoppingCartController);

	ShoppingCartController.$inject = [ 'UserService', 'CartService','CheckoutService','sharedProperties','FlashService', '$rootScope','$location' ];

	function ShoppingCartController(UserService, CartService,CheckoutService,sharedProperties,FlashService, $rootScope,$location) {
		var sc = this;
		CartService.user = null;
		
		console.log();
		initController();

		function initController() {
			loadCurrentUser();
			ShowHideControls();
		}
		
		function loadCurrentUser() {
			sc.user = $rootScope.globals.currentUser.username;
		}
		
		function ShowHideControls() {
			$rootScope.flash = null;
			$("#licartsymbol").show();
			$("#liuser").show();
			$('#divCheckOut').show();
			$("#lihome").show();
		}
		
		//Checkout item from cart
		sc.CheckOut = function() {
			sharedProperties.setValue("checkoutmessage",null);
			sc.dataLoading = true;
			CheckoutService.CheckOutItemsFromCart(sc.user).then(function(items) {
				if (items != null && items.success != null && !items.success) {
					FlashService.Error(items.message);
					sc.dataLoading = false;
				} else if (items != null && items.data != null) {
					sharedProperties.setValue("checkoutmessage",items.data);
					$location.path('/checkout');
				}
			});
		}
		
		//Get cart items
		var spCartItems = sharedProperties.getValue("cartItems");
		if(spCartItems != 'undefined' && spCartItems != null){
			sc.cartItems = spCartItems;
			UpdateCartInformation(spCartItems);
			UpdateCartTable(sc,UserService, CartService,CheckoutService,sharedProperties,FlashService, $rootScope,$location);
		}else{
			CartService.getCartItems(sc.user).then(function(cartItems) {
				if (cartItems != null && cartItems.success != null && !cartItems.success) {
					FlashService.Error(cartItems.message);
				} else if (cartItems != null && cartItems.data != null && cartItems.data.length > 0) {
					sharedProperties.setValue("cartItems",cartItems.data)
					sc.cartItems = cartItems.data;
					UpdateCartInformation(cartItems.data);
					UpdateCartTable(sc,UserService, CartService,CheckoutService,sharedProperties,FlashService, $rootScope,$location);
				}	else{
					FlashService.Error("Your shopping cart is empty.Please add items to your chart");
					$('#divCheckOut').hide();
				}
			});
		}
	}
	
	function UpdateCartTable(sc,UserService, CartService,CheckoutService,sharedProperties,FlashService, $rootScope,$location){
		var spCartItems = sharedProperties.getValue("cartItems");
		sc.quantity = 1;
			
		// get the total price for all items currently in the cart
		sc.getTotalPrice = function() {
			var total = 0;
			for (var i = 0; i < sc.cartItems.length; i++) {
				var item = sc.cartItems[i];
				if(item != null && item != undefined){
					total += parseFloat(item.price, 10);
				}
			}
			return total;
		}
			
		// get the total quantity of all items currently in the cart
		sc.getTotalCount = function() {
			return sc.cartItems.length;
		}
			
		//Removes item from cart
		sc.RemoveFromCart = function(cartItems,cartItem, index) {
			CartService.removeItemFromCart(sc.user,cartItem.id).then(function(items) {
			if (items != null && items.success != null && !items.success) {
				FlashService.Error(items.message);
			} else if (items != null && items.data != null) {
					if (items.data.indexOf("Deleted item id") == -1) {
						FlashService.Error(items.data);
					} else {
						FlashService.Success("'" + cartItem.title + "'  was removed from your shopping cart.");
						var value = parseInt($("#cartCount").text(), 10) - 1;
						if(value != 0){
							$("#cartCount").text(value);
						} else {
							$("#cartCount").text('');
							$("#divCheckOut").hide();
						}
						cartItems.splice(index, 1);
						if(spCartItems != 'undefined' && spCartItems != null){
							findAndRemove(spCartItems,"id",cartItem.id);
						}
					}
				}
			});
		  }
	}
	
	function findAndRemove(array, property, value) {
		   $.each(array, function(index, result) {
		      if(result[property] == value) {
		          //Remove from array
		          array.splice(index, 1);
		      }    
		   });
		}
})();