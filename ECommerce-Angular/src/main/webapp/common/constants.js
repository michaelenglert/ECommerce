//Namespaced Constants
var ECommerceApp;
// MyAppName Namespace
(function(ECommerceApp) {
	// MyAppName.Constants Namespace
	(function(Constants) {
		// Private
		function createConstant(name, val) {
			Object.defineProperty(ECommerceApp.Constants, name, {
				value : val,
				writable : false
			});
		}

		// Public

		// initialize messageResource.js
		messageResource.init({
			// path to directory containing config.properties
			filePath : 'resources'
		});

		// load config.properties file
		messageResource
				.load(
						'config',
						function() {
							// load file callback

							// get value corresponding to a key from
							// config.properties

							Constants.SERVICEURL = createConstant("SERVICEURL",
									 messageResource.get('ui', 'config'));
							Constants.USERSERVICEURL = createConstant(
									"USERSERVICEURL",
									 messageResource.get('hostname', 'config') + 'rest/service/json/login');
							Constants.PRODUCTSERVICEURL = createConstant(
									"PRODUCTSERVICEURL",
									messageResource.get('hostname', 'config') + 'rest/service/json/getallitems');
							Constants.CARTSERVICEGETITEMSURL = createConstant(
									"CARTSERVICEGETITEMSURL",
									messageResource.get('hostname', 'config') + 'rest/service/json/getcartitems');
							Constants.CARTSERVICEADDITEMSURL = createConstant(
									"CARTSERVICEADDITEMSURL",
									messageResource.get('hostname', 'config') + 'rest/service/json/additemtocart');
							Constants.CARTSERVICEREMOVEITEMSURL = createConstant(
									"CARTSERVICEREMOVEITEMSURL",
									messageResource.get('hostname', 'config') + 'rest/service/json/removeitemfromcart');
							Constants.CHECKOUTSERVICEURL = createConstant(
									"CHECKOUTSERVICEURL",
									messageResource.get('hostname', 'config') + 'rest/service/json/checkout');
						});

		ECommerceApp.Constants = Constants;
	})(ECommerceApp.Constants || (ECommerceApp.Constants = {}));
})(ECommerceApp || (ECommerceApp = {}));