/**
 * 
 */


var app = angular.module("login", []);
	app.controller("loginCntrl",function($scope, $http) {
		$scope.login = function(username, password) {
			alert(username + " " + password);
			$http({
				url : 'http://localhost:9100/shoppingcart/login',
				method : "POST",
				data : {userName:username, password:password}
				}).then(function(response) {
				console.log(response);
				}, function(response) { // optional
				console.log(response);

				});
			
		}

	})