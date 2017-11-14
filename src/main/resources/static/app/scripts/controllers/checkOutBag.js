/**
 * 
 */

var app = angular.module('checkOut', []);
app.controller('checkOutCntrl', function($http, $scope, $window){
	var host = $window.location.host;
	var url = $window.location.href;
	var info = url.split("?")[1];
	var userId = info.split("=")[1];
	var productId = 1; //sample
	var text = Math.random().toString(36).substr(2, 5)+0123456789;
	$scope.randomOrderId = text;
	$scope.date = new Date();
	$http({
		 method : "GET",
			url : "http://" + host + "/shoppingcart/shoppingCart/summaryBagDetailsController/"
					+ userId
					+ "/"
					+ productId,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		  }).then(function mySuccess(response) {
			  console.log(response.data);
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
		      $scope.history1 = response.data.productList;
		    }, function myError(response) {
		      $scope.myWelcome = response.statusText;
		  });
	
})
