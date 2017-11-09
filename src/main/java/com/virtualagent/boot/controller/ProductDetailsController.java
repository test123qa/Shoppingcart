package com.virtualagent.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtualagent.boot.dto.ShoppingCartDTO;
import com.virtualagent.boot.pojo.ProductDetails;
import com.virtualagent.boot.service.ShoppingCartService;

@RestController
@RequestMapping("/shoppingCart")
public class ProductDetailsController {

	 @Autowired
	 private ShoppingCartService shoppingCartService;
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json", value ="/productDetails")
    public Map<String, Object> showProductDetails(@RequestBody ShoppingCartDTO shoppingCartDTO,@RequestHeader HttpHeaders headers, HttpServletRequest request) {
		logger.info("Start of showProductDetails() method in controller");
		logger.debug("User click product Id "+shoppingCartDTO.getProductId());
		
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
