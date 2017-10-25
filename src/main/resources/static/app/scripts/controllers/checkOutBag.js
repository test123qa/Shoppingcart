/**
 * 
 */
var proApp = angular.module('bagApp', []);
proApp
		.controller(
				'bagCtrl',
				function($scope, $http, $window) {
//					angular
//							.element(document)
//							.ready(
					
					$scope.getCurrentYear = function() {
										var loc = (document.location)
												.toString();
										var qryPar = loc.split("?")[1];
										var eachPar = qryPar.split(";");
										var product = new Object();
//										alert(eachPar[0].split("=")[1]);
										var productId = eachPar[0].split("=")[1];
//										alert(eachPar[1].split("=")[1]);
										var userId = eachPar[1].split("=")[1];
										$http(
												{
													method : "GET",
													url : "http://localhost:8080/shoppingcart/shoppingCart/checkOutBagDetailsController/"
															+ userId
															+ "/"
															+ productId,
													// data: product,
													headers : {
														'Content-Type' : 'application/json',
														'Accept' : 'application/json'
													}
												})
												.then(
														function mySuccess(
																response) {
															// $scope.myWelcome
															// = response.data;
															// alert(JSON.stringify(response));
															var str = response.data;
															// tr =
															// str.replace("\\",
															// "");
//															alert(JSON.stringify(str))
															return JSON.stringify(str);
//															console
//																	.log(JSON
//																			.stringify(str));
//															// console.log(JSON.stringify(str));
//															$scope.initData = JSON
//																	.stringify(str);
//															$scope.proObject = str.length;
////															alert('repeat '
////																	+ $scope.proObject)
//
//															$scope.templates = [
//																	{
//																		name : 'shoppingBagTemplate.html',
//																		url : 'shoppingBagTemplate.html'
//																	},
//																	{
//																		name : 'shoppingBagTemplate.html',
//																		url : 'shoppingBagTemplate.html'
//																	},
//																	{
//																		name : 'shoppingBagTemplate.html',
//																		url : 'shoppingBagTemplate.html'
//																	},
//																	{
//																		name : 'shoppingBagTemplate.html',
//																		url : 'shoppingBagTemplate.html'
//																	},
//																	{
//																		name : 'shoppingBagTemplate.html',
//																		url : 'shoppingBagTemplate.html'
//																	},
//																	{
//																		name : 'shoppingBagTemplate.html',
//																		url : 'shoppingBagTemplate.html'
//																	},
//																	{
//																		name : 'shoppingBagTemplate.html',
//																		url : 'shoppingBagTemplate.html'
//																	}, ];

//															for (var prochVar = 0; prochVar < str.length; prochVar++) {
//																var proCheckout = str[prochVar];
//																console
//																		.log(proCheckout.productName)
//																// $scope.bagProName
//																// =
//																// proCheckout;
//																// var proTemp =
//																// '<div
//																// class="lineItem
//																// dashedResgistryLine"
//																// id="lineitem_37384129_3_2"
//																// style="border-top:
//																// none;">'+
//																// '<div
//																// class="qvAddToBagMessage"></div>'+
//																// '<div
//																// id="bagErrMsg_3"
//																// class="bagErrorMsg
//																// hidden"></div>'+
//																// '<div
//																// class="clearFloats"></div>'+
//																// '<div
//																// class="lineItemError
//																// hidden
//																// notification-error"'+
//																// 'id="lineItemError_3"></div>'+
//																// '<div
//																// class="colItem">'+
//																// '<span
//																// class="itemImage">
//																// <a'+
//																// 'href="#"'+
//																// 'class="productUrl">
//																// <img
//																// class="productImage"'+
//																// 'src="#">'+
//																// '</a>'+
//																// '</span>
//																// <span
//																// class="itemDescription">'+
//																// '<div
//																// class="itemName">'+
//																// '<a'+
//																// 'href="https://www.macys.com"'+
//																// 'class="productName
//																// productUrl"
//																// id="href_3_productDesc">'+
//																//																				
//																// '<h3><b><span
//																// id="bagProName"
//																// ng-model="bagProName"></span></b></h3></a>'+
//																// '</div> <span
//																// id="lineItemColor_3"
//																// class="itemColor">
//																// <span'+
//																// 'class="label">Color:
//																// </span> <span
//																// class="valColor">Heather</span><br>'+
//																// '</span>'+
//																// '<span
//																// ng-model="bagProcDesc"></span>'+
//																//																	
//																// '<br>'+
//																// '<span
//																// class="itemWebId">
//																// <span
//																// class="label">Web'+
//																// ' ID: </span>
//																// <span
//																// id="webID_4591384"
//																// class="valWebId"
//																// ng-model="valWebId"></span><br>'+
//																// '</span>'+
//																// '</span>'+
//																// '</div>'+
//																// '<div
//																// class="colDelivery">'+
//																// '<span
//																// class="stockAvailable">
//																// In Stock:
//																// Usually
//																// ships'+
//																// 'within 2
//																// business
//																// days.
//																// </span>'+
//																// '</div>'+
//																// '<div
//																// class="colQtyUpdate">'+
//																// '<select
//																// id="qtySelect"
//																// class="selectQty
//																// qtySelect_3">'+
//																// '<option
//																// value="1">1</option>'+
//																// '<option
//																// value="2">2</option>'+
//																// '<option
//																// value="3">3</option>'+
//																// '<option
//																// value="4">4</option>'+
//																// '<option
//																// value="5">5</option>'+
//																// '<option
//																// value="6">6</option>'+
//																// '</select>'+
//																// '</div>'+
//																// '<div
//																// id="priceDesc">'+
//																// '</div>'+
//																// '<div
//																// class="makeChoice"
//																// id="makeChoiceColors_3"></div>'+
//																// '<div
//																// class="makeChoice"
//																// id="makeChoiceSizes_3"></div>'+
//																// '<div
//																// class="makeChoice"
//																// id="makeChoiceTypes_3"></div>'+
//																// '<div
//																// class="promoDetails">'+
//																// '<div
//																// id="promotionDesc_3"></div>'+
//																// '<div
//																// id="promotionPrice_3"
//																// class="promoPrice"></div>'+
//																// '</div>'+
//																// '<div
//																// style="padding-bottom:
//																// 9px; width:
//																// 100%">'+
//																// '<div
//																// class="clearFloats"></div>'+
//																// '<div
//																// style="float:
//																// right;
//																// padding-top:
//																// 3px;">'+
//																// '<div
//																// class="secondary
//																// linkOverride
//																// removeLink"'+
//																// 'id="remove_item_37384129_3_2">Remove</div>'+
//																// '&nbsp;&nbsp;'+
//																// '</div>'+
//																// '</div>'+
//																// '<span'+
//																// 'id="isRegistryLineItem"'+
//																// 'class="lineItemAttributes
//																// isRegistryLineItem
//																// hidden"></span>'+
//																// '<div
//																// class="clearFloats"></div>'+
//																// '</div>';
//
//																alert(prochVar)
//																if (prochVar == 0) {
//																	$scope.template = $scope.templates[prochVar];
//																} else if (prochVar == 1) {
//																	$scope.template2 = $scope.templates[prochVar];
//																} else if (prochVar == 2) {
//																	$scope.template3 = $scope.templates[prochVar];
//																} else if (prochVar == 3) {
//																	$scope.template4 = $scope.templates[prochVar];
//																} else if (prochVar == 4) {
//																	$scope.template5 = $scope.templates[prochVar];
//																} else if (prochVar == 5) {
//																	$scope.template6 = $scope.templates[prochVar];
//																}
//
//																// $scope.template2
//																// =
//																// $scope.templates[1];
//
//																// $('#ProductTemplate').append(proTemp);
//																// $("#bagProName").val(proCheckout.productName)
//																// //
//																// alert($("#bagProName").val())
//																// debugger;
//																$scope.bagProName = proCheckout.productName;
//																$scope.bagProcDesc = proCheckout.procDesc;
//																$scope.valWebId = proCheckout.proId;
//																alert($scope.bagProName)
//															}

															// $template =
															// $('#lineitem_37384129_3_2').clone()
															// ;
															// var node =
															// document.createElement("div");
															// var textnode =
															// document.createTextNode(proTemp);
															// node.appendChild(textnode);
															// document.getElementById("ProductTemplate").appendChild(node);
															// for(var i=0;
															// i<=3; i++){
															// document.getElementById("ProductTemplate").innerHTML
															// = proTemp;
															// }
															// document.getElementById("ProductTemplate").innerHTML
															// = proTemp;

														},
														function myError(
																response) {
															$scope.myWelcome = response.statusText;
														});
									}
//					);
				});