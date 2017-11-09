package com.virtualagent.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.virtualagent.boot.domain.ShoppingCart;
import com.virtualagent.boot.dto.ShoppingCartDTO;
import com.virtualagent.boot.pojo.ProductDetails;
import com.virtualagent.boot.service.ShoppingCartService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sahan on 4/9/2016.
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json", value="/addProdcut")
    public ShoppingCart addProductItem(@RequestBody ShoppingCartDTO shoppingCartDTO, HttpServletRequest request) {
        return shoppingCartService.saveProducts(shoppingCartDTO, request);
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
    
    @RequestMapping(method = RequestMethod.GET, value = "/showMyBag/{productId}")
    public String showMyBag(@PathVariable("productId") Long productId, HttpServletRequest request) {
    	return shoppingCartService.showMyBag(productId, request);
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/checkOutBagDetailsController/{productId}/{userId}")
    public Map<String, Object> checkOutBagDetailsController(@PathVariable("userId") Long productId, @PathVariable("productId") Long userId, HttpServletRequest request) {
    	return shoppingCartService.checkOutBagDetailsService(userId, request);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/summaryBagDetailsController/{productId}/{userId}")
    public List<ProductDetails> summaryBagDetailsController(@PathVariable("userId") Long productId, @PathVariable("productId") Long userId) {
    	return shoppingCartService.summaryBagDetailsService(userId);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteProductById/{proId}")
    public void deleteProductFromCart(@PathVariable("proId") Long product_id) {
    	shoppingCartService.deleteProductFromCart(product_id);
    
    }
}
