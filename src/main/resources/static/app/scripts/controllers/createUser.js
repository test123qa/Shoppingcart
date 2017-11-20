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
function validate(){
		if($scope.payload.userName==''){
			return false;
		}if($scope.payload.firstName==''){
			return false;
		}if($scope.payload.lastName==''){
			return false;
		}if($scope.payload.email==''){
			return false;
		}if($scope.payload.password==''){
			return false;
		}else{
			return true;
		}
	}
	$scope.onSubmit = function() {
		var host = $window.location.host;
		if(validate())
	{
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
	}else{
		return false;
	}
	}
	
	$scope.back = function(){
		var host = $window.location.host;
		var landingUrl = "http://" + host + "/shoppingcart";
		$window.location.href = landingUrl;
	}
})