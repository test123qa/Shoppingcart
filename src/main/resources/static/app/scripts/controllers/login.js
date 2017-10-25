/**
 * 
 */


var app = angular.module("login", []);
	app.controller("loginCntrl",function($scope, $http, $window) {
		var host = $window.location.host;
		$scope.login = function(username, password) {
			$http({
				url : "http://" + host +"/shoppingcart/login",
				method : "POST",
				data : {userName:username, password:password}
				}).then(function(response) {
				console.log(response);
				}, function(response) { // optional
				console.log(response);

				});
			
		}

	})