/**
 * 
 */

var app = angular.module("createUser", []);
app.controller("createUserCntrl", function($scope, $http, $window) {

	$scope.payload = {
		firstName : "",
		lastName : "",
		name : "",
		email : "",
		pwd : "",
		DOB : "",

	}

	$scope.onSubmit = function() {
		var host = $window.location.host;
		$http({
			url : "http://" + host + "/shoppingcart/profile",
			method : "POST",
			data : $scope.payload
		}).then(function(response) {
			console.log(response);
			alert("Account Created Successfully !!!!")
			alert("Click on the image to go to Home ...")
		}, function(response) {
			console.log(response);

		});
	}

})
