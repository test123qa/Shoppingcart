/**
 * 
 */

var app = angular.module("menActiveWare", []);

app.controller("menActiveWareCntrl", function($scope, $http, $window) {

	$http({
		method : "GET",
		url : "http://localhost:8080/shoppingcart/products/getProductBy/MEN/MEN_ACTIVE_WARE"

	}).then(function mySuccess(response) {
		alert(JSON.stringify(response.data));
		console.log(JSON.stringify(response.data));
		$scope.productsByCat = response.data;
	}, function myError(response) {
		$scope.myWelcome = response.statusText;
	});
	
	$scope.addToCart=function(proId){
		alert(proId)
		var stocks = 1; //dummy value
		var status = 'PURCHASED'; //dummy value
		var module = 'MEN';
		var subModule = 'MEN_ACTIVE_WARE';
		var host = $window.location.host;
	    	      var landingUrl = "http://" + host + "/shoppingcart/app/views/productDetails.html";
	    	      landingUrl = landingUrl+"?proId="+proId+";stock="+stocks+";status="+status+";MODULE="+module+";SUB-MODULE="+subModule;
	    	      console.log(landingUrl)
	  	        $window.location.href = landingUrl;
		
		
//		var product = new Object();
//		  product.productId = proId;
//		  product.stock = 1; //dummy value
//		  product.status = 'PURCHASED'; //dummy value
//		  
//		  $http({
//	          method: "POST",
//	          url: "http://localhost:8080/shoppingcart/shoppingCart/AddToCart",
//	          data: product,
//		  headers: {
//		        'Content-Type': 'application/json',
//		        'Accept': 'application/json' 
//		    }
//	      }).then(function mySuc(response){
//	    	      console.log(JSON.stringify(response.data));
//	    	      //$stateParams.proDetails = product;
//	    	      var host = $window.location.host;
//	    	      var landingUrl = "http://" + host + "/shoppingcart/app/views/productDetails.html";
//	    	      landingUrl = landingUrl+"?proId="+pro.id+";stock="+stocks+";status="+status;
//	    	      console.log(landingUrl)
//	  	        $window.location.href = landingUrl;
//	    	        app.config(function($stateProvider,$routeProvider) {
//	    	    	    $routeProvider
////	    	    	    .when("/shoppingcart", {
////	    	    	        templateUrl : 'productDetails.html',
////	    	    	        controller : "pdpCtrl"
////	    	    	    })
//	    	    	    
//	    	    	    $stateProvider
//	    	    	    .state('home', {
//	    	    	        url: '/home',
//	    	    	        templateUrl: 'partial-home.html'
//	    	    	    })
//
//	    	    	    
//	    	    	});
//	      }, function myErr(response){
//	    	  //console.log(JSON.stringify(response.statusText));
//	      });
		  
	}

})
