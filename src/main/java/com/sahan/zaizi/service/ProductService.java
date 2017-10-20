package com.sahan.zaizi.service;

import com.sahan.zaizi.Exception.ShoppingCartException;
import com.sahan.zaizi.domain.Product;
import com.sahan.zaizi.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
		products.add(new Product(35.75d, 1000, "Pears baby soap for Kids", "Soap", 'a'));
		products.add(new Product(45.50d, 500, "Signal Tooth Brushes Size in (L, M, S)", "Tooth Brushe", 'a'));
		products.add(new Product(1500.0d, 100, "Casual Shirt imported from France", "Shirt", 'a'));
		products.add(new Product(1000.0d, 400, "Leather bag imported from USA ", "Office Bag", 'a'));
		products.add(new Product(450.0d, 800, "Hot Water Bottles", "Bottle", 'a'));
		products.add(new Product(2500.0d, 800, "Imported wrist watches from swiss", "Wrist Watch", 'a'));
		products.add(new Product(45000.0d, 800, "3G/4G capability", "Mobile Phone", 'a'));
		products.add(new Product(300.0d, 800, "Head and Shoulders Shampoo", "Shampoo", 'a'));
		products.add(new Product(550.0d, 800, "Imported Leather Wallets from AUS", "Leather Wallets", 'a'));
		products.add(new Product(85000.0d, 800, "Imported Canon camera from USA", "Camera", 'a'));
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

}
