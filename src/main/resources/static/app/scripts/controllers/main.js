'use strict';

/**
 * @ngdoc function
 * @name demoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the demoApp
 */


var app = angular.module('myShoppingList', []);
app.controller('myCtrl', function($scope, $http, $window) {
	var host = $window.location.host;
  $http({
    method : "GET",
    url : "http://" + host + "/shoppingcart/products"
  }).then(function mySuccess(response) {
      console.log(JSON.stringify(response.data));
      $scope.products = response.data;
    }, function myError(response) {
      $scope.myWelcome = response.statusText;
  });

  $scope.addToCart = function(pro,stocks) {
	  if(stocks == undefined){
	  alert('Please add any product to cart then only proceed.');
	  return;
	  }

	  $scope.itemDetails = {
			  productId : pro.id,
			  stock : stocks,
			  status : "ACTIVE",
	  }
	  alert($scope.itemDetails);
	  alert(JSON.stringify($scope.itemDetails));
	  /*var host = $window.location.host;
	        alert(host);
	        var landingUrl = "http://" + host + "/";
	        alert(landingUrl);
	        $window.location.href = landingUrl;*/
	        
	        $http({
	  		  url : "http://" + host + "/shoppingcart/shoppingCart/addProdcut",
	  		  method : "POST",
	  		  data : $scope.itemDetails
	  		  }).then(function(response) {
	  		  console.log(response);
	  		  }, function(response) { // optional
	  		  console.log(response);

	  		  });

	  };
	  
	  
  
  
});





