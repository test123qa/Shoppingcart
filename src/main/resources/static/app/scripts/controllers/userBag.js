/**
 * 
 */

var proApp = angular.module('userBagApp', []);

proApp.controller('userBagCtrl', function($scope, $http, $window) {
	//document.getElementById('men').style.display = 'none';
	//document.getElementById('women').style.display = 'none';
	//document.getElementById('kids').style.display = 'none';
	var iniProData = undefined;
	var module = undefined;
	var subModule = undefined;
    angular.element(document).ready(function () {
        var loc = (document.location).toString();
        var qryPar = loc.split("?")[1];
        var eachPar = qryPar.split(";");
        //var product = new Object();
	  var productId = eachPar[0].split("=")[1];
	  //var productCount = eachPar[1].split("=")[1];
	  var userId = 1;
	  var host = $window.location.host;
	  console.log(productId+"-----"+userId)
        $http({
            method : "GET",
            url : "http://" + host + "/shoppingcart/shoppingCart/showMyBag/"+productId+"/"+userId,
            headers: {
    	        'Content-Type': 'application/json',
    	        'Accept': 'application/json' 
    	        }
          }).then(function mySuccess(response) {
        	  console.log(response.data);
              $scope.bagPayload = response.data;
            }, function myError(response) {
              $scope.myWelcome = response.statusText;
          });
        
    });
    
    fnKeepShopping=function(){
    	//bagSummaryView
    }
    
    fnViewBagCheckOut=function(){
   
    	var host = $window.location.host;
	      var landingUrl = "http://" + host + "/shoppingcart/app/views/checkOutBag2.html";
	      landingUrl = landingUrl+"?productId="+productId+";userId="+userId;
	      console.log(landingUrl)
        $window.location.href = landingUrl;
    }
    
});

