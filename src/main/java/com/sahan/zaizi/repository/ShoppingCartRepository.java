package com.sahan.zaizi.repository;

import com.sahan.zaizi.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sahan on 4/9/2016.
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByStatus(String status);
    
    @Query(value = "SELECT p.description, sc.amount, sc.stock, (select sum(stock) from shopping_cart shop where shop.user_id=:userId) as totalCount, (select sum(amount) from shopping_cart shop where shop.user_id=:userId) as totalAmount FROM shopping_cart sc, user u, product p where sc.product_id=p.id and sc.user_id=u.id and sc.user_id=:userId and sc.product_id=:productId" , nativeQuery = true)
	public List<Object[]> showMyBag(@Param("userId") Long userId, @Param("productId") Long productId);
}
