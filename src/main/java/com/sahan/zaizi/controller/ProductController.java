package com.sahan.zaizi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sahan.zaizi.Exception.ShoppingCartException;
import com.sahan.zaizi.domain.Product;
import com.sahan.zaizi.service.ProductService;

/**
 * Created by sahan on 4/9/2016.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
	// the logger
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAll(
			@RequestParam(required = false, value = "category", defaultValue = "a") char category) {
		logger.debug("BEGIN:entered into the getAll() method in the " + this.getClass().getName());
		try {
			return new ResponseEntity<>(productService.findAll(category), HttpStatus.OK);
		} catch (ShoppingCartException exception) {
			logger.error(" some thing went wrong while getting allthe product's " + exception.getMessage());
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		logger.debug("BEGIN: entered into the saveProduct() method in the " + this.getClass().getName());
		try {
			return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
		} catch (ShoppingCartException exception) {
			logger.error("some thing went wrong while saving the poduct " + exception.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/delete/{productId}")
	public ResponseEntity<?> deleteProductByID(@PathVariable("productId") long productId) {
		try {
			productService.deleteProductById(productId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ShoppingCartException exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getProduct/{productId}")
	public ResponseEntity<?> getProductByID(@PathVariable("productId") long productId) {
		logger.debug("BEGIN:get getProductByID in the " + this.getClass().getName());
		try {
			Product p = productService.getProductById(productId);
			if (p != null) {
				return new ResponseEntity<>(p, HttpStatus.OK);
			}
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (ShoppingCartException ex) {
			logger.error("some thing went wrong while getting the product by id " + productId + " the error message "
					+ ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/getProductByName/{productName}")
	public ResponseEntity<?> getProductByName(@PathVariable("productName") String productName) {
		logger.debug("BEGIN:method getProductByName() in the " + getClass().getName());
		List<Product> products = null;
		try {

			products = productService.getProductByName(productName);

			if (products != null && products.size() != 0) {
				return new ResponseEntity<>(products, HttpStatus.OK);

			} else {
				return new ResponseEntity<>("no product's found with your search", HttpStatus.NO_CONTENT);
			}
		} catch (ShoppingCartException sce) {
			logger.error("ERROR  " + sce.getMessage());
			return new ResponseEntity<>(sce.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
