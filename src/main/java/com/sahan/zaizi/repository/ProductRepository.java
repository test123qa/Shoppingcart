package com.sahan.zaizi.repository;

import com.sahan.zaizi.domain.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by sahan on 4/8/2016.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByCategory(String category);

	@Query(value = "select id, name, description, quantity from Product where category = ?1 and subCategory = ?2")
	public List<Product> findByCategory(String category, String subCategory);

}
