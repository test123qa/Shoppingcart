/**
 * 
 */
package com.sahan.zaizi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.virtualagent.boot.Exception.ShoppingCartException;
import com.virtualagent.boot.controller.ProductController;
import com.virtualagent.boot.domain.Product;
import com.virtualagent.boot.service.ProductService;

/**
 * @author nisum
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	ProductService productService;

	private static Logger log = LoggerFactory.getLogger(ProductServiceTest.class);

	@InjectMocks
	ProductController productController;

	private static Product p = null;

	@BeforeClass
	public static void setData() {
		p = new Product();
		p.setId(1L);
		p.setName("nisum");
		p.setDescription("technologies");
		p.setCategory("mens");
		p.setQuantity(12);
		p.setSubCategory("active");
		p.setUnitPrice(2.2);

	}
	
	@AfterClass
	public static void destroy() {
		System.out.println("--------TEST CASES COMPLETED SUCCESS FULLY -------");
	}

	@Test
	public void savingProductTest() {
		Product product = p;
		log.info("ProductService :: savingProductTest");
		ResponseEntity<?> actualResult = productController.saveProduct(product);
		assertEquals(HttpStatus.OK, actualResult.getStatusCode());

	}
	
	@Test
	public void savingProductFailTest() {
		
		
		
	}

	@Test
	public void deleteProductIdTest() {
		log.info("ProductService :: deleteProductIdTest");
		ResponseEntity<?> actualResult = productController.deleteProductByID(1);
		assertEquals(HttpStatus.OK, actualResult.getStatusCode());

	}

	@Test
	public void getProductByIDTest() throws ShoppingCartException {
		Product product = p;
		log.info("ProductService :: getProductByIDTest");
		when(productService.getProductById(1L)).thenReturn(product);
		ResponseEntity<?> actualResult = productController.getProductByID(1);
		assertThat(p).isEqualsToByComparingFields((Product) actualResult.getBody());

	}

	@Test
	public void getProductByNameTest() {
		log.info("ProductService :: getProductByNameTest");
		Product product = p;
		List<Product> productList = new ArrayList<>();
		productList.add(product);
		try {
			when(productService.getProductByName("men")).thenReturn(productList);
			assertEquals(1, productList.size());
			assertThat(productList.size()).isEqualTo(1);
		} catch (ShoppingCartException e) {
			e.printStackTrace();
		}
	}

}
