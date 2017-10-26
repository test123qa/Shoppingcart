package com.sahan.zaizi.controller;

import com.sahan.zaizi.domain.ShoppingCart;
import com.sahan.zaizi.dto.ShoppingCartDTO;
import com.sahan.zaizi.pojo.ProductDetails;
import com.sahan.zaizi.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sahan on 4/9/2016.
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json", value="/addProdcut")
    public ShoppingCart addProductItem(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.saveProducts(shoppingCartDTO);
    }


    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value="/show")
    public List<ShoppingCart> getAll(){
        return shoppingCartService.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value ="/{id}")
    public ShoppingCart updateProductItem(@RequestBody ShoppingCartDTO shoppingCartDTO, @PathVariable("id") Long ids) {
        return shoppingCartService.updateProduct(shoppingCartDTO, ids);
    }

    @RequestMapping(method = RequestMethod.DELETE, value ="/{id}")
    public void deleteProductItem(@PathVariable("id") Long product_id) {
        shoppingCartService.deleteProduct(product_id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void ClearCart( Object object) {
        shoppingCartService.clearShoppingCart(object);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/purchase/{id}")
    public void purchaseProducts(@PathVariable("id") Long id) {
        shoppingCartService.purchaseProducts(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/showMyBag/{productId}/{userId}")
    public String showMyBag(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId) {
    	return shoppingCartService.showMyBag(productId, userId);
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/checkOutBagDetailsController/{productId}/{userId}")
    public List<ProductDetails> checkOutBagDetailsController(@PathVariable("userId") Long productId, @PathVariable("productId") Long userId) {
    	return shoppingCartService.checkOutBagDetailsService(userId);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteProductById/{proId}")
    public void deleteProductFromCart(@PathVariable("proId") Long product_id) {
    	shoppingCartService.deleteProductFromCart(product_id);
    
    }
}
