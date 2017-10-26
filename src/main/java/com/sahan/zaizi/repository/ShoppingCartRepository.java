package com.sahan.zaizi.repository;

import com.sahan.zaizi.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;


/**
 * Created by sahan on 4/9/2016.
 */

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByStatus(String status);
    
    @Query(value = "SELECT p.description, sc.amount, sc.stock, (select sum(stock) from shopping_cart shop where shop.user_id=:userId) as totalCount, (select sum(amount) from shopping_cart shop where shop.user_id=:userId) as totalAmount FROM shopping_cart sc, user u, product p where sc.product_id=p.id and sc.user_id=u.id and sc.user_id=:userId and sc.product_id=:productId" , nativeQuery = true)
	public List<Object[]> showMyBag(@Param("userId") Long userId, @Param("productId") Long productId);
	

	@Query(value = "SELECT sc.product_id, sum(sc.amount) as amount, sum(sc.stock) as stock  FROM shoppingCart.shopping_cart sc  where sc.user_id =:userId group by sc.product_id" , nativeQuery = true)
	public List<Object[]> getCartDetails(@Param("userId") Long userId);
	
	@Query(value = "select product.id, product.name, product.description, product.unit_price from shoppingCart.product product where product.id=:proId" , nativeQuery = true)
	public List<Object[]> getProductDetails(@Param("proId") BigInteger proId);

	@Modifying
	@Transactional
	@Query(value = "delete from shopping_cart where product_id=:product_id", nativeQuery=true)
	public void deleteProductById(@Param("product_id") Long product_id);
	
	
}
