function UpdateCartInformation(cartItems){
		if (cartItems != null && cartItems != undefined && cartItems.length > 0) {
			// Add the cart count
				$("#cartCount").text(cartItems.length);
				ChangeCartSymbolColor();
		} else{
			$("#cartCount").text('');
		}
	};
	
	function ChangeCartSymbolColor(){
		// Change the color of cart symbol when the
		// items are added to cart
		$("#cartCount").addClass('cart-color');
		$("#cartSymbol").addClass('cart-color');
	};