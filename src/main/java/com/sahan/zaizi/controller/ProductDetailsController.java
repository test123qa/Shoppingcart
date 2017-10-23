package com.sahan.zaizi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sahan.zaizi.dto.ShoppingCartDTO;
import com.sahan.zaizi.pojo.ProductDetails;
import com.sahan.zaizi.service.ShoppingCartService;

@RestController
@RequestMapping("/shoppingCart")
public class ProductDetailsController {

	 @Autowired
	 private ShoppingCartService shoppingCartService;
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json", value ="/productDetails")
    public List<ProductDetails> showProductDetails(@RequestBody ShoppingCartDTO shoppingCartDTO,@RequestHeader HttpHeaders headers) {
		logger.info("Start of showProductDetails() method in controller");
		logger.debug("User click product Id "+shoppingCartDTO.getProductId());
		return shoppingCartService.getPoductDetails(shoppingCartDTO.getProductId());
    }
	
	
}
