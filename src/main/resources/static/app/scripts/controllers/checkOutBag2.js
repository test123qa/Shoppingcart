/**
 * 
 */
var proApp = angular.module('checkOutBagApp', []);
proApp.controller('proCrl', function($scope, $http, $window) {
	var host = $window.location.host;
	var loc = (document.location)
	.toString();
var qryPar = loc.split("?")[1];
var eachPar = qryPar.split(";");
var product = new Object();
//alert(eachPar[0].split("=")[1]);
var productId = eachPar[0].split("=")[1];
//alert(eachPar[1].split("=")[1]);
var userId = eachPar[1].split("=")[1];
	 $http({
		 method : "GET",
			url : "http://" + host + "/shoppingcart/shoppingCart/checkOutBagDetailsController/"
					+ userId
					+ "/"
					+ productId,
			// data: product,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		  }).then(function mySuccess(response) {
		      //$scope.myWelcome = response.data;
		      //alert(response);
			  console.log(JSON.stringify(response.data))
		      $scope.history1 = response.data;
		    }, function myError(response) {
		      $scope.myWelcome = response.statusText;
		  });
	 
	 $scope.fnRemoveItemFromCart = function(proId){
		 var okToRefresh = confirm("Do you really want to remove this item from cart?");
		 if(okToRefresh){
			 $http({
				 method: "DELETE",
				 url: "http://" +host + "/shoppingcart/shoppingCart/deleteProductById/"+proId
			 }).then(function mySuccess(response) {
				  console.log(JSON.stringify(response.data))
			    }, function myError(response) {
			      $scope.myWelcome = response.statusText;
			  });
		 }
	 }
});