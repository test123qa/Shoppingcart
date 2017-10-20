/**
 * 
 */

var app = angular.module("menActiveWare", []);

app.controller("menActiveWareCntrl", function($scope, $http) {

	$http({
		method : "GET",
		url : "http://localhost:9100/shoppingcart/products/MEN/MEN_ACTIVE_WARE"

	}).then(function mySuccess(response) {
		alert(JSON.stringify(response.data));
		console.log(JSON.stringify(response.data));
		$scope.productsByCat = response.data;
	}, function myError(response) {
		$scope.myWelcome = response.statusText;
	});

})
