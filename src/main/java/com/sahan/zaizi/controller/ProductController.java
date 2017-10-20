package com.sahan.zaizi.controller;

import com.sahan.zaizi.domain.Product;
import com.sahan.zaizi.repository.ProductRepository;
import com.sahan.zaizi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sahan on 4/9/2016.
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Product> getAll() {
		return productService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/{category}/{subCategory}")
	public List<Product> getProductsByCategory(@PathVariable String category, @PathVariable String subCategory) {
		return productRepository.findByCategory(category, subCategory);
	}

}
