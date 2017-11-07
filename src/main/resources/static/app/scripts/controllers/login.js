/**
 * 
 */

var proApp = angular.module('loginApp', []);

proApp.controller('loginMainCtrl', function($scope, $http, $window) {
    angular.element(document).ready(function () {
    	 var loc = (document.location).toString();
         var qryPar = loc.split("?")[1];
         //alert(qryPar);
         if(qryPar == "error"){
        	 alert("Wrong user id or password!");
        	 $window.location.href = loc.split("?")[0];
         }
    });
    
});

