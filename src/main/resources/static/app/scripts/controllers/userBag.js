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
	var productId = undefined;
	var userId = undefined;
    angular.element(document).ready(function () {
        var loc = (document.location).toString();
        var qryPar = loc.split("?")[1];
        var eachPar = qryPar.split(";");
        //var product = new Object();
	  productId = eachPar[0].split("=")[1];
	  //var productCount = eachPar[1].split("=")[1];
	  //userId = 1;
	  var host = $window.location.host;
	  console.log(productId+"-----")
        $http({
            method : "GET",
            url : "http://" + host + "/shoppingcart/shoppingCart/showMyBag/"+productId,
            headers: {
    	        'Content-Type': 'application/json',
    	        'Accept': 'application/json' 
    	        }
          }).then(function mySuccess(response) {
        	  console.log(response.data);
              $scope.bagPayload = response.data;
              /*****Set Loggedin User name*****/
      		if(response.data.userName != "" && response.data.userName != "null" && response.data.userName != null){
          		
          		var htmlData = '<table style="width: 100%;">';
          		htmlData += '<tr>';
          		htmlData += '<th> Hi '+response.data.userName+'</th>';
          		htmlData += '<th><div class="dropdown"><img src="../images/drop-down.png" width="25" height="25">';
          		htmlData += '<div class="dropdown-loginMenu"><a href="#">Acount</a> <a href="#">Order</a> <a href="/shoppingcart/logout">logout</a></div></div></th>';
          		htmlData += '</tr></table>';
          		document.getElementById("loggedinUserDetails").innerHTML = htmlData;
          	}
      		/*****End Set Loggedin User name*****/
            }, function myError(response) {
              $scope.myWelcome = response.statusText;
          });
        
    });
    
    fnKeepShopping=function(){
    	//bagSummaryView
    }
    
    fnViewBagCheckOut=function(){
    	var logginUserId = document.getElementById("userId").value;
    	console.log("Loggin user id..."+logginUserId);
    	var host = $window.location.host;
	      var landingUrl = "http://" + host + "/shoppingcart/app/views/checkOutBag2.html";
	      landingUrl = landingUrl+"?productId="+productId+";userId="+logginUserId;
	      console.log(landingUrl)
        $window.location.href = landingUrl;
    }
    
});

