/**
 * 
 */

var proApp = angular.module('indexApp', []);

proApp.controller('indexMainCtrl', function($scope, $http, $window) {
    angular.element(document).ready(function () {
    	 var loc = (document.location).toString();
         var qryPar = loc.split("?")[1];
         //alert(qryPar);
         if(qryPar == "logout"){
        	 alert("You have successfully logout!");
        	 $window.location.href = loc.split("?")[0];
         }else if(qryPar == "signup"){
        	 alert("You have successfully Signup!");
        	 $window.location.href = loc.split("?")[0];
         }
         
    	var host = $window.location.host;
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
                  url : "http://" + host + "/shoppingcart/"+ip+"/generateUserId",
                  headers: {
          	        'Content-Type': 'application/json',
          	        'Accept': 'application/json' 
          	        }
                }).then(function mySuccess(loginSuccessResponse) {
                	$scope.loginPayload = loginSuccessResponse.data;
                	/*****Set Loggedin User name*****/
                	if(loginSuccessResponse.data.userName != "" && loginSuccessResponse.data.userName != "null" && loginSuccessResponse.data.userName != null){
                		
                		var htmlData = '<table style="width: 100%;">';
                		htmlData += '<tr>';
                		htmlData += '<th> Hi '+loginSuccessResponse.data.userName+'</th>';
                		//htmlData += '<th><a href="/shoppingcart/logout"> logout</a></th>';
                		htmlData += '<th><div class="dropdown"><img src="app/images/drop-down.png" width="25" height="25">';
                		htmlData += '<div class="dropdown-loginMenu"><a href="#">Acount</a> <a href="#">Order</a> <a href="/shoppingcart/logout">logout</a></div></div></th>';
                		htmlData += '</tr></table>';
//                		var htmlData = '<label>Hi '+loginSuccessResponse.data.userName+'</label>';
//                		htmlData += '<a href="/shoppingcart/logout"> logout</a>';
                		document.getElementById("loggedinUserDetails").innerHTML = htmlData;
                		document.getElementById("signinSignupId").style.display = "none";
                	}
                	/*****End Set Loggedin User name*****/
                  }, function myError(response) {
                });
            }, function myError(response) {
              $scope.myWelcome = response.statusText;
          });
    	
        
        
    });
    
});

