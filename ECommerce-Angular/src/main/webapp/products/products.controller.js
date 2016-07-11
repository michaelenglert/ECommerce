(function() {
	'use strict';

	angular.module('app').controller('ProductsController', ProductsController);
	
	ProductsController.$inject = ['$location', 'UserService', 'ProductService','CartService','sharedProperties','FlashService', '$rootScope' ,'superCache'];
	function ProductsController($location,UserService, ProductService, CartService,sharedProperties,FlashService,$rootScope,superCache) {
		var pc = this;
		pc.user = null;
		
		
		pc.serviceurl = 'http://' + $location.host()  + '/' + ECommerceApp.Constants.SERVICEURL;
		initController();

		function initController() {
			loadCurrentUser();
			ShowHideControls();
		}
		function loadCurrentUser() {
			pc.user = $rootScope.globals.currentUser.username;
		}
		
		function ShowHideControls() {
			$rootScope.flash = null;
			$("#licartsymbol").show();
			$("#liuser").show();
			$("#lihome").hide();
		}
		
		//Get cart items
		var spCartItems = sharedProperties.getValue("cartItems");
		if(spCartItems != 'undefined' && spCartItems != null){
			UpdateCartInformation(spCartItems);
		}else{
			CartService.getCartItems(pc.user).then(function(cartItems) {
				if (cartItems != null && cartItems.success != null && !cartItems.success) {
					FlashService.Error(cartItems.message);
				} else if (cartItems != null && cartItems.data != null && cartItems.data.length > 0) {
					sharedProperties.setValue("cartItems",cartItems.data)
					UpdateCartInformation(cartItems.data);
				}
			});
		}
		
		//Get all items
		if(superCache.put("products") != 'undefined' && superCache.get("products") != null){
			pc.items = superCache.get("products");
		} else {
			ProductService.GetAllItems().then(function(items) {
				if (items != null && items.success != null && !items.success) {
					FlashService.Error(items.message);
				} else if (items != null && items.data != null) {
					pc.items = items.data;
					superCache.put("products", pc.items);
				}
			});
		}
		
		//Add to Cart
		pc.AddToCart = function(product, $event) {
			var addtocart = true;
			var spCartItems = sharedProperties.getValue("cartItems");
			if(spCartItems != 'undefined' && spCartItems != null){
				$.each(spCartItems, function(key,value) {
				 if(value.id == product.id)
					 addtocart = false;
				});
			}
			if(addtocart)
			{
				CartService.addItemToCart(pc.user, product.id).then(function(items) {
					if (items != null && items.success != null && !items.success) {
						FlashService.Error(items.message);
					} else if (items != null && items.data != null && parseInt(items.data.cartSize) > 0) {
						// Add the cart count
						if (!$("#cartCount").text() || 0 === $("#cartCount").text().length){
							$("#cartCount").text(1);
						} else {
							var value = parseInt($("#cartCount").text(), 10) + 1;
							$("#cartCount").text(value);
						}
						if(spCartItems!= null && spCartItems != undefined){
							spCartItems.push({id: product.id, title: product.title, imagePath: product.imagePath, itemId: product.id, price: product.price});
						} else {
							sharedProperties.setValue("cartItems",[{id: product.id, title: product.title, imagePath: product.imagePath, itemId: product.id, price: product.price}]);
						}
						ChangeCartSymbolColor();	
		
						// Change the color of the buttons when the
						// items are added to cart
						angular.element($event.currentTarget).addClass("disable_a_href");
						angular.element($event.currentTarget).find('.btn-success').removeClass('btn-success');
						angular.element($event.currentTarget).addClass('btn-warning');
					}
				});
				
			} else{
				FlashService.Error("'" + product.title + "' has already been added to the cart. Please try adding other items to the cart.");
			}
			
		};
	};
	
})();