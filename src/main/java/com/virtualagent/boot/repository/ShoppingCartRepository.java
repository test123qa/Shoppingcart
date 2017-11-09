package com.virtualagent.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.virtualagent.boot.domain.ShoppingCart;

import java.math.BigInteger;
import java.util.List;


/**
 * Created by sahan on 4/9/2016.
 */

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByStatus(String status);
    
    @Query(value = "SELECT p.description, sc.amount, p.imageUrl, sc.stock, (select sum(stock) from shopping_cart shop where shop.user_id=:userId and shop.status='ACTIVE') as totalCount, (select sum(amount) from shopping_cart shop where shop.user_id=:userId and shop.status='ACTIVE') as totalAmount FROM shopping_cart sc, shopping_user u, product p where sc.product_id=p.id and sc.user_id=u.id and sc.user_id=:userId and sc.product_id=:productId and sc.status='ACTIVE'" , nativeQuery = true)
	public List<Object[]> showMyBag(@Param("userId") Long userId, @Param("productId") Long productId);
	

	@Query(value = "SELECT sc.product_id, sum(sc.amount) as amount, sum(sc.stock) as stock  FROM shopping_cart sc  where sc.user_id =:userId and sc.status = 'ACTIVE' group by sc.product_id" , nativeQuery = true)
	public List<Object[]> getCartDetails(@Param("userId") Long userId);
	
	@Query(value = "SELECT sc.product_id, sum(sc.amount) as amount, sum(sc.stock) as stock  FROM shopping_cart sc  where sc.user_id =:userId and sc.status = 'PURCHASED' group by sc.product_id" , nativeQuery = true)
	public List<Object[]> getPurchasedDetails(@Param("userId") Long userId);
	
	@Query(value = "select product.id, product.name, product.description, product.unit_price, product.imageUrl from product product where product.id=:proId" , nativeQuery = true)
	public List<Object[]> getProductDetails(@Param("proId") BigInteger proId);

	@Modifying
	@Transactional
	@Query(value = "delete from shopping_cart where product_id=:product_id", nativeQuery=true)
	public void deleteProductById(@Param("product_id") Long product_id);

	@Modifying
	@Transactional
	@Query(value="update shopping_cart set status = 'PURCHASED' where user_id =:user_id", nativeQuery=true)
	public void updateProductStatus(@Param("user_id") Long user_id);
	
	
}
