/**
 * 
 */

var proApp = angular.module('procApp', []);

proApp.controller('proCtrl', function($scope, $http, $window) {
	var host = $window.location.host;
	document.getElementById('men').style.display = 'none';
	document.getElementById('women').style.display = 'none';
	document.getElementById('kids').style.display = 'none';
	var iniProData = undefined;
	var module = undefined;
	var subModule = undefined;
    angular.element(document).ready(function () {
        var loc = (document.location).toString();
        var qryPar = loc.split("?")[1];
        var eachPar = qryPar.split(";");
        var product = new Object();
	  product.productId = eachPar[0].split("=")[1];
	  product.stock = eachPar[1].split("=")[1];
	  product.status = eachPar[2].split("=")[1];
	  module = eachPar[3].split("=")[1];
	  subModule = eachPar[4].split("=")[1];
        $http({
            method : "POST",
            url : "http://" + host +"/shoppingcart/shoppingCart/productDetails",
            data: product,
            headers: {
    	        'Content-Type': 'application/json',
    	        'Accept': 'application/json' 
    	        }
          }).then(function mySuccess(response) {
              console.log(JSON.stringify(response.data));
              var proData = JSON.stringify(response.data);
              for(var i=0; i<response.data.length; i++) {
            	  iniProData = (response.data)[i];
            	  console.log(iniProData.productName)
            	  $scope.productName = iniProData.productName;
            	  $scope.priceSale =  iniProData.unitPrice;
            	  $scope.proQut = iniProData.stock;
            	  $scope.mainDesc = iniProData.procDesc;
            	  $scope.webId = "Web Id "+iniProData.productId;
              }
            }, function myError(response) {
              $scope.myWelcome = response.statusText;
          });
        if(module.trim()=='MEN') {
        	document.getElementById('men').style.display = 'block';
        } else if(module.trim()=='WOMEN'){
        	document.getElementById('women').style.display = 'block';
        } else if(module.trim()=='KIDS'){ //for future refrence we write else if
        	document.getElementById('kids').style.display = 'block';
        } 
        
    });
    
    $scope.addToBag= function(){
  	var productId = iniProData.productId;
  	var productCount = document.getElementById("proQut").value;
  	
	    	      var landingUrl = "http://" + host + "/shoppingcart/app/views/userBag.html";
	    	      landingUrl = landingUrl+"?productId="+productId+";productCount="+productCount;
	    	      console.log(landingUrl)
	  	        $window.location.href = landingUrl;
  	
  	var product = new Object();
	  product.productId = iniProData.productId;
	  product.stock = document.getElementById("proQut").value;
	  product.status = 'PURCHASED';
	  $http({
          method: "POST",
          url: "http://" + host + "/shoppingcart/shoppingCart/addProdcut",
          data: product,
	  headers: {
	        'Content-Type': 'application/json',
	        'Accept': 'application/json' 
	    }
      }).then(function mySuc(response){
    	      console.log(JSON.stringify(response.data));
  	        
      }, function myErr(response){
      });
	  
    }
    
});