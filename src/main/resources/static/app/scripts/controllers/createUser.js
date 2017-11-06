/**
 * 
 */

var app = angular.module("createUser", []);
app.controller("createUserCntrl", function($scope, $http, $window) {

	$scope.payload = {
		userName : "",
		firstName : "",
		lastName : "",
		name : "",
		email : "",
		password : "",
		DOB : "",

	}

	$scope.onSubmit = function() {
		var host = $window.location.host;
		$http({
			url : "http://" + host + "/shoppingcart/registerUser",
			method : "POST",
			data : $scope.payload
		}).then(function(response) {
			console.log(response);
			//alert("Account Created Successfully !!!!")
			//alert("Click on the image to go to Home ...")
			var landingUrl = "http://" + host + "/shoppingcart?signup";
  	      console.log(landingUrl)
	        $window.location.href = landingUrl;
		}, function(response) {
			console.log(response);

		});
	}

})
