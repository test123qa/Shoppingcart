/**
 * 
 */

var app = angular.module("menActiveWare", []);

app.controller("menActiveWareCntrl", function($scope, $http, $window) {

	var hostName = $window.location.host;
	$http({
		method : "GET",
		url : "http://" + hostName + "/shoppingcart/products/getProductBy/MEN/MEN_ACTIVE_WARE",

	}).then(function mySuccess(response) {
		console.log(response.data);
		console.log(response.data.userName);
		console.log(response.data.productList);
		console.log(JSON.stringify(response.data));
		/*****Set Loggedin User name*****/
		if(response.data.userName != "" && response.data.userName != "null" && response.data.userName != null){
    		
    		var htmlData = '<table style="width: 100%;">';
    		htmlData += '<tr>';
    		htmlData += '<th> Hi '+response.data.userName+'</th>';
    		htmlData += '<th><div class="dropdown"><img src="../images/drop-down.png" width="25" height="25">';
    		htmlData += '<div class="dropdown-loginMenu"><a href="#">Acount</a> <a href="#">Order</a> <a href="/shoppingcart/logout">logout</a></div></div></th>';
    		htmlData += '</tr></table>';
    		document.getElementById("loggedinUserDetails").innerHTML = htmlData;
    	}
		/*****End Set Loggedin User name*****/
		$scope.productsByCat = response.data.productList;
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
