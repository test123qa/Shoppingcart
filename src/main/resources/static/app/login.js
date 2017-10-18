/**
 * 
 */

//var app = angular.module("login", []);
//app.controller("loginCntrl", function($scope, $http) {
//	
//	$http({
//	    method : "GET",
//	    url : "http://localhost:9100/shoppingcart/"
//	  }).then(function mySuccess(response) {
//	      console.log(JSON.stringify(response.data));
//	    }, function myError(response) {
//	      $scope.myWelcome = response.statusText;
//	  });
//
//	$scope.login = function(user, pwd) {
//
//		if (user != admin && user == undefined) {
//			alert('Wrong Credentials');
//			return;
//		}
//		if(pwd != admin && pwd == undefined){
//			alert('Wrong Credentials');
//			return;
//		}
//	}
//
//})


var app = angular.module("login", []);
	app.controller("loginCntrl", function($scope, $http) {
		
		/** $http({
		    method : "GET",
		    url : "http://localhost:9100/shoppingcart/"
		  }).then(function mySuccess(response) {
		      console.log(JSON.stringify(response.data));
		    }, function myError(response) {
		      $scope.myWelcome = response.statusText;
		  }); */

		$scope.login = function(user, pwd) {
alert(12)
			if (user != "admin" && user == undefined) {
				alert('Wrong Credentials');
				return;
			}
			if(pwd != "admin" && pwd == undefined){
				alert('Wrong Credentials');
				return;
			}
		}

	})