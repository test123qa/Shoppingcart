/**
 * 
 */

var proApp = angular.module('indexApp', []);

proApp.controller('indexMainCtrl', function($scope, $http, $window) {
    angular.element(document).ready(function () {
    	$http({
            method : "GET",
            url : "https://api.ipify.org",
            headers: {
    	        'Content-Type': 'application/json',
    	        'Accept': 'application/json' 
    	        }
          }).then(function mySuccess(response) {
        	  console.log(response);
        	  var ip = response.data;
        	  $http({
                  method : "GET",
                  url : "http://localhost:8080/shoppingcart/"+ip+"/generateUserId",
                  headers: {
          	        'Content-Type': 'application/json',
          	        'Accept': 'application/json' 
          	        }
                }).then(function mySuccess(response) {
                  }, function myError(response) {
                });
            }, function myError(response) {
              $scope.myWelcome = response.statusText;
          });
    	
        
        
    });
    
});

