package com.sahan.zaizi.repository;

import com.sahan.zaizi.domain.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by sahan on 4/8/2016.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByCategory(char category);

	public Product findById(Long ID);

	@Query("select id, name, description, quantity from Product  where name like %?1%")
	public List<Product> findByName(String productName);

	@Query("select id, name, description, quantity from Product where category = ?1 and subCategory = ?2")
	public List<Product> findByCategory(String category, String subCategory);

}
