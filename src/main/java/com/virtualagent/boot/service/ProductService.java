package com.virtualagent.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtualagent.boot.Exception.ShoppingCartException;
import com.virtualagent.boot.domain.Product;
import com.virtualagent.boot.repository.ProductRepository;

/**
 * Created by sahan on 4/8/2016.
 */
@Service
@Transactional
public class ProductService {

	// the logger
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ProductRepository productRepository;

	private static List<Product> products = new ArrayList<>();

	static {
		products.add(new Product(35.75d, 1000, "Champion Men's Logo Graphic T-Shirt", "T-Shirt", "MEN", "MEN_ACTIVE_WARE","../images/1.jpeg"));
		products.add(new Product(45.50d, 500, "adidas Men's Tricot Track Pants", "Tracks", "WOMEN",
				"WOMEN_ACTIVE_WARE","../images/2.jpeg"));
		products.add(
				new Product(1500.0d, 100, "adidas Men's Essential Tricot Track Jacket", "Shirt", "WOMEN", "WOMEN_ACTIVE_WARE","../images/3.jpeg"));
		products.add(
				new Product(1000.0d, 400, "Leather bag imported from USA ", "Office Bag", "KIDS", "KIDS_ACTIVE_WARE","../images/4.jpeg"));
		products.add(new Product(450.0d, 800, "adidas Men's Essential Tricot Track Jacket", "Jackets", "MEN", "MEN_ACTIVE_WARE","../images/10.jpeg"));
		products.add(new Product(2500.0d, 800, "adidas Originals Men's Fleece Trefoil Hoodie", "Hoodies", "MEN",
				"MEN_ACTIVE_WARE","../images/5.jpeg"));
		products.add(new Product(45000.0d, 800, "Under Armour Men's Tech™ Short Sleeve Shirt", "T-Shirt", "MEN", "MEN_ACTIVE_WARE","../images/6.jpeg"));
		products.add(new Product(300.0d, 800, "Puma Men's P48 Core Track Jacket", "Hoodies", "MEN", "MEN_ACTIVE_WARE","../images/7.jpeg"));
		products.add(new Product(550.0d, 800, "Champion Men's Vapor® Select Training Pants", "Pants", "MEN",
				"MEN_ACTIVE_WARE","../images/8.jpeg"));
		products.add(new Product(85000.0d, 800, "Under Armour Men's 10 Tech Graphic Shorts", "Shorts", "MEN", "MEN_ACTIVE_WARE","../images/9.jpeg"));
	}

	public void saveInitialBatch() {
		productRepository.save(products);

	}

	public List<Product> findAll(char category) throws ShoppingCartException {
		logger.debug("BEGIN:findAll() Mehtod the" + this.getClass().getName());
		try {
			return productRepository.findByCategory(category);
		} catch (NoResultException ex) {
			throw new ShoppingCartException("no data found");
		} catch (Exception ex) {
			throw new ShoppingCartException("some thing went wrong while getting data");
		}

	}

	public long saveProduct(Product product) throws ShoppingCartException {
		logger.debug("saveProduct()  Mehtod in the " + this.getClass().getName());
		Product p = null;
		try {
			p = productRepository.save(product);
		} catch (Exception ex) {
			logger.error("some thing went wrong while saving the product");
			throw new ShoppingCartException("some thing went wrong while saving product");
		}
		logger.debug("END:saveProduct()  Mehtod in the " + this.getClass().getName());
		return p.getId();
	}

	public Product getProductById(Long id) throws ShoppingCartException {
		logger.debug("BEGIN:getProductById()  Mehtod in the " + this.getClass().getName());
		Product p = null;
		try {

			return p = productRepository.findById(id);
		} catch (Exception ex) {
			logger.error("some thing went wrong while getting  product ByID " + id);
			throw new ShoppingCartException("some thing went wrong while saving product");
		}
	}

	public void deleteProductById(Long productID) throws ShoppingCartException {
		logger.debug("BEGIN:deleteProductById()  Mehtod in the " + this.getClass().getName());
		try {
			productRepository.delete(productID);
		} catch (Exception ex) {
			logger.error("some thing went wrong while deleting  product ByID " + productID);
			throw new ShoppingCartException("some thing went wrong while saving product");
		}
	}

	public List<Product> getProductByName(String name) throws ShoppingCartException

	{
		try {
			return productRepository.findByName(name);
		} catch (Exception ex) {
			throw new ShoppingCartException("some thing went wrong while fetching data");
		}
	}

	public List<Product> getProductByCategory(String category, String subCategory) throws ShoppingCartException {
		try {
			return productRepository.findByCategory(category, subCategory);
		} catch (Exception e) {
			throw new ShoppingCartException("some thing went wrong while fetching data");
		}
	}

}
