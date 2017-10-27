/**
 * 
 */

var app = angular.module('checkOut', []);
app.controller('checkOutCntrl', function($http, $scope, $window){
	var host = $window.location.host;
	var url = $window.location.href;
	var info = url.split("?")[1];
	var userId = info.split("=")[1];
	var productId = 1; //sample
	var text = Math.random().toString(36).substr(2, 5)+0123456789;
	$scope.randomOrderId = text;
	$scope.date = new Date();
	$http({
		 method : "GET",
			url : "http://" + host + "/shoppingcart/shoppingCart/summaryBagDetailsController/"
					+ userId
					+ "/"
					+ productId,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		  }).then(function mySuccess(response) {
			  console.log(JSON.stringify(response.data))
		      $scope.history1 = response.data;
		    }, function myError(response) {
		      $scope.myWelcome = response.statusText;
		  });
	
})
