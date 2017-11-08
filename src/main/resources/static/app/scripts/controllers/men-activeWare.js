/**
 * 
 */

var app = angular.module("menActiveWare", []);

app.controller("menActiveWareCntrl", function($scope, $http, $window) {

	var hostName = $window.location.host;
	$scope.myImages = ["1.jpeg", "2.jpeg", "3.jpeg", "4.jpeg","5.jpeg","6.jpeg","7.jpeg","8.jpeg","9.jpeg","10.jpeg"];
	$scope.getImagePath = function(imageName) {
		return "http://"+hostName +"/shoppingcart/app/images/" + imageName;
		};
	$http({
		method : "GET",
		url : "http://" + hostName + "/shoppingcart/products/getProductBy/MEN/MEN_ACTIVE_WARE",

	}).then(function mySuccess(response) {
		console.log(JSON.stringify(response.data));
		$scope.productsByCat = response.data;
	}, function myError(response) {
		$scope.myWelcome = response.statusText;
	});
	
	$scope.addToCart=function(proId){
		proId = proId[0];
		var stocks = 1; //dummy value
		var status = 'ACTIVE'; //dummy value
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
