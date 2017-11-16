package com.virtualagent.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtualagent.boot.ShoppingCartApplication;
import com.virtualagent.boot.dto.ShoppingCartDTO;
import com.virtualagent.boot.pojo.ProductDetails;
import com.virtualagent.boot.service.ShoppingCartService;
import com.virtualagent.boot.util.ShoppingCartUtil;

@RestController
@RequestMapping("/shoppingCart")
public class ProductDetailsController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	 
	@Autowired
	private ShoppingCartUtil shoppingCartUtil;
	
	Logger logger = LogManager.getLogger(ShoppingCartApplication.class);
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json", value ="/productDetails")
    public Map<String, Object> showProductDetails(@RequestBody ShoppingCartDTO shoppingCartDTO,@RequestHeader HttpHeaders headers, HttpServletRequest request) {
		
		logger.info("Start of showProductDetails() method in controller");
		System.out.println(" Kishore Testing "+request.getPathInfo());
		System.out.println(" Kishore 1111111 "+request.getParameterMap());
		//If user logged in, show all the cookies 
		if(request.getRemoteUser() != null && request.getRemoteUser() != ""){
			Cookie guestCookie = shoppingCartUtil.getShoppingCartCookie(request, "guestTokenId");
			Cookie loggedUserCookie = shoppingCartUtil.getShoppingCartCookie(request, "userTokenID");
			logger.info("URL: "+request.getRequestURL()+"/"+shoppingCartDTO.getProductId()
					+",GuestTokenID:"+guestCookie.getValue()
					+",LoggedUserTokenID:"+loggedUserCookie.getValue()+",");
		} 
		//If user not logged in then show only guest cookie token
		else {
			Cookie guestCookie = shoppingCartUtil.getShoppingCartCookie(request, "guestTokenId");
			logger.info("URL: "+request.getRequestURL()+"/"+shoppingCartDTO.getProductId()
					+",GuestTokenID:"+guestCookie.getValue()
					+",LoggedUserTokenID:,");
		}
		
		List<ProductDetails> productDetailsList = shoppingCartService.getPoductDetails(shoppingCartDTO.getProductId());
		Map<String, Object> productMap = new HashMap<>();
		HttpSession session = request.getSession();
		if(request.getRemoteUser() != null && request.getRemoteUser() != ""){
			productMap.put("userName", request.getRemoteUser());
		}else{
			productMap.put("userName", "");
		}
		productMap.put("productDetailsList", productDetailsList);
		return productMap;
    }
	
	
}
