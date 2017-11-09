/**
 * 
 */
var proApp = angular.module('checkOutBagApp', []);
proApp.controller('proCrl', function($scope, $http, $window) {
	var host = $window.location.host;
	var loc = (document.location)
	.toString();
var qryPar = loc.split("?")[1];
var eachPar = qryPar.split(";");
var product = new Object();
//alert(eachPar[0].split("=")[1]);
var productId = eachPar[0].split("=")[1];
//alert(eachPar[1].split("=")[1]);
var userId = eachPar[1].split("=")[1];
	 $http({
		 method : "GET",
			url : "http://" + host + "/shoppingcart/shoppingCart/checkOutBagDetailsController/"
					+ userId
					+ "/"
					+ productId,
			// data: product,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		  }).then(function mySuccess(response) {
		      console.log(response.data.productList);
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
			  console.log(JSON.stringify(response.data))
		      $scope.history1 = response.data.productList;
		    }, function myError(response) {
		      $scope.myWelcome = response.statusText;
		  });
	 
	 $scope.fnRemoveItemFromCart = function(proId){
		 var okToRefresh = confirm("Do you really want to remove this item from cart?");
		 if(okToRefresh){
			 $http({
				 method: "DELETE",
				 url: "http://" +host + "/shoppingcart/shoppingCart/deleteProductById/"+proId
			 }).then(function mySuccess(response) {
				  console.log(JSON.stringify(response.data))
			    }, function myError(response) {
			      $scope.myWelcome = response.statusText;
			  });
		 }
	 }
	 $scope.checkout = function(){
		 var host = $window.location.host;
		 var loc = $window.location.href;
		 var userId = window.location.href.split("?")[1].split(";")[1].split("=")[1];
		 var landingUrl = "http://" + host + "/shoppingcart/app/views/checkOutBag.html"+"?userId=" +userId;
		 $window.location.href = landingUrl;
		 
	 }
});