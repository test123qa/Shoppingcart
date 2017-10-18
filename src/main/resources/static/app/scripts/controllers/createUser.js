/**
 * 
 */

var app = angular.module("createUser", []);
app.controller("createUserCntrl", function($scope, $http) {

	$scope.payload = {
		firstName : "",
		lastName : "",
		name : "",
		email : "",
		pwd : "",
		DOB : "",

	}

	$scope.onSubmit = function() {
		$http({
			url : 'http://localhost:9100/shoppingcart/profile',
			method : "POST",
			data : $scope.payload
		}).then(function(response) {
			console.log(response);
		}, function(response) {
			console.log(response);

		});
	}

})
