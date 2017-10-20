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
			alert("Account Created Successfully !!!!")
			alert("Click on the image to go to Home ...")
		}, function(response) {
			console.log(response);

		});
	}

})
