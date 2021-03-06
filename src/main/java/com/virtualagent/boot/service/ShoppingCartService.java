package com.virtualagent.boot.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.virtualagent.boot.domain.Product;
import com.virtualagent.boot.domain.ShoppingCart;
import com.virtualagent.boot.domain.User;
import com.virtualagent.boot.dto.ShoppingCartDTO;
import com.virtualagent.boot.pojo.ProductDetails;
import com.virtualagent.boot.repository.ProductRepository;
import com.virtualagent.boot.repository.ShoppingCartRepository;
import com.virtualagent.boot.repository.UserRepository;
import com.virtualagent.boot.service.ProductService;
import com.virtualagent.boot.util.ShoppingCartUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by sahan on 4/9/2016.
 */
@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ShoppingCartUtil shoppingCartUtil;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    
    @Autowired
    private ProductService productService;
    
    Logger logger = Logger.getLogger(ShoppingCartService.class);

    public ShoppingCart saveProducts(ShoppingCartDTO shoppingCartDTO, HttpServletRequest request) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = productRepository.findOne(shoppingCartDTO.getProductId());
        shoppingCart.setProduct(product);
        System.out.println(" Is user logged int :::: "+request.getRemoteUser());
        Cookie cookie = null;
		if(request.getRemoteUser() != null && request.getRemoteUser() != ""){
			cookie = shoppingCartUtil.getShoppingCartCookie(request, "userTokenID");
		}
		else {
			cookie = shoppingCartUtil.getShoppingCartCookie(request, "guestTokenId");
		}
    	Long userId;
    	if(cookie != null){
    		String value = cookie.getValue();
    		 userId = userRepository.findByCookieTokenId(value).getId();
    	}else{
    		System.out.println("Oops!...Cookie is not available...");
    		System.out.println("Setting default userId...."+1);
    		userId = 1L;
    	}
    	System.out.println("user id on adding product to cart===>   "+userId);
        shoppingCart.setUser(userRepository.findOne(userId));
        shoppingCart.setStatus(shoppingCartDTO.getStatus());
        shoppingCart.setDate(new Date());
        shoppingCart.setStock(shoppingCartDTO.getStock());
        shoppingCart.setAmount(product.getUnitPrice() * shoppingCartDTO.getStock());

        return shoppingCartRepository.save(shoppingCart);
    }


    public List<ShoppingCart> findAll() {
    		return shoppingCartRepository.findAll();
       // return shoppingCartRepository.findByStatus("NOT_PURCHASED");
    }

    public ShoppingCart updateProduct(ShoppingCartDTO shoppingCartDTO, Long id) {
        ShoppingCart updateItem = shoppingCartRepository.findOne(id);
        updateItem.setStock(shoppingCartDTO.getStock());
        updateItem.setAmount(updateItem.getProduct().getUnitPrice() * shoppingCartDTO.getStock());
        return shoppingCartRepository.save(updateItem);
    }

    public void deleteProduct(Long product_id) {
        shoppingCartRepository.delete(product_id);
    }

    public void clearShoppingCart(Object object) {
        shoppingCartRepository.delete(findAll());
    }


    public List<ShoppingCart> findByPurchased() {
        return shoppingCartRepository.findByStatus("ACTIVE");
    }


    public void purchaseProducts(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findOne(id);
        shoppingCart.setStatus("ACTIVE");
        shoppingCartRepository.save(shoppingCart);
    }
    
    public List<ProductDetails> getPoductDetails(Long id) {
    	logger.info("Start of getPoductDetails() method in ShoppingCartService");
    	List<Product> listObj =  productRepository.findAll();
    logger.debug("size of products avali le in db "+listObj.size());
    	Iterator<Product> itr = listObj.iterator();
    	ProductDetails pdObj = null;
    	while(itr.hasNext()) {
    		Product scObj = (Product)itr.next();
    		if(scObj.getId() == id) {
    			pdObj = new ProductDetails();
    			pdObj.setProductId(scObj.getId());
    			pdObj.setProductName(scObj.getName());
    			pdObj.setProcDesc(scObj.getDescription());
    			pdObj.setImageUrl(scObj.getImageUrl());
    			//pdObj.setQuantity(scObj.getProduct().getQuantity());
//    			pdObj.setStock(scObj.getStock());
//    			pdObj.setAmmount(scObj.getUnitPrice());
//    			pdObj.setUserName(scObj.getUser().getName());
    			pdObj.setUnitPrice(scObj.getUnitPrice());
    		}
    	}
    //	System.out.println("product details "+shoppingCart);
    	List<ProductDetails> listObj2 = new ArrayList<ProductDetails>();
    	listObj2.add(pdObj);
    return listObj2;
    }
    
    public String showMyBag(Long productId, HttpServletRequest request) {
    	System.out.println("In shoppingCartService...showMyBag() method...");
    	String responseData = "";
    	if(request.getSession(false) != null){
    		System.out.println("Session id....."+request.getSession(false).getId());
    		if(request.getSession(false).getAttribute("userDetails") != null){
    			User user = (User) request.getSession(false).getAttribute("userDetails");
    			System.out.println("User detaisl..."+user);
    		}
    		
    	}
    	System.out.println(" Is user logged into the site :::: "+request.getRemoteUser());
        Cookie cookie = null;
		if(request.getRemoteUser() != null && request.getRemoteUser() != ""){
			cookie = shoppingCartUtil.getShoppingCartCookie(request, "userTokenID");
		}
		else {
			cookie = shoppingCartUtil.getShoppingCartCookie(request, "guestTokenId");
		}
  
    	Long userId;
    	if(cookie != null){
    		String value = cookie.getValue();
    		 //userId = Long.parseLong(value.split(",")[1]);
    		userId = userRepository.findByCookieTokenId(value).getId();
    	}else{
    		System.out.println("Oops!...Cookie is not available...");
    		System.out.println("Setting default userId...."+1);
    		userId = 1L;
    	}
    	System.out.println("show bag user id....."+userId);
    	List<Object[]> bagDataList = shoppingCartRepository.showMyBag(userId, productId);
    	Object[] bagData = bagDataList.get(0);
    	if(request.getSession(false) != null){
    		System.out.println("Session is existed.....");
    		responseData = "{\"productDesc\" : \""+bagData[0]+"\", \"productAmount\" : \""+bagData[1]+"\", \"imageUrl\" : \""+bagData[2]+"\",\"productCount\" : \""+bagData[3]+"\", \"totalProductCount\" : \""+bagData[4]+"\", \"totalAmount\" : \""+bagData[5]+"\", \"userId\" : \""+userId+"\", \"userName\" : \""+request.getRemoteUser()+"\"}";
    	}else{
    		System.out.println("Session not is existed.....");
    		responseData = "{\"productDesc\" : \""+bagData[0]+"\", \"productAmount\" : \""+bagData[1]+"\", \"imageUrl\" : \""+bagData[2]+"\",\"productCount\" : \""+bagData[3]+"\", \"totalProductCount\" : \""+bagData[4]+"\", \"totalAmount\" : \""+bagData[5]+"\", \"userId\" : \""+userId+"\", \"userName\" : \"\"}";
    	}
    	return responseData;
    }
    
    public Map<String, Object> checkOutBagDetailsService(Long UserId, HttpServletRequest request) {
    	List<ProductDetails> proHistory = new ArrayList<ProductDetails>();
    	List<Object[]> cartListObj = shoppingCartRepository.getCartDetails(UserId);
    	Map<String, Object> productMap = new HashMap<>();
    	for(int i=0; i<cartListObj.size();i++) {
    		Object[] eCartObj = cartListObj.get(i);
    		BigInteger proId = (BigInteger)eCartObj[0];
    		List<Object[]> proListObj = shoppingCartRepository.getProductDetails(proId);
    		Object[] proDet = proListObj.get(0);
    		
    		ProductDetails  pdObj = new ProductDetails();
    		pdObj.setProId((BigInteger)proDet[0]);
    		pdObj.setProductName((String)proDet[1]);
    		pdObj.setProcDesc((String)proDet[2]);
    		pdObj.setUnitPrice(((Double)proDet[3]));
    		pdObj.setAmmount(((Double)eCartObj[1]));
    		pdObj.setImageUrl((String)proDet[4]);
    		pdObj.setStockDec((BigDecimal)eCartObj[2]);
    		String productDetails = "{\"proId\":\""+proDet[0]+"\",\"name\":\""+proDet[1]+"\",\"desc\":\""+proDet[2]+"\",\"unit_price\":\""+proDet[3]+"\",\"imageUrl\":\""+proDet[4]+"\",\"amount\":\""+eCartObj[1]+"\",\"stock\":\""+eCartObj[2]+"\"}";
    		proHistory.add(pdObj);
    	}
    	
    	HttpSession session = request.getSession();
		if(request.getRemoteUser() != null && request.getRemoteUser() != ""){
			productMap.put("userName", request.getRemoteUser());
		}else{
			productMap.put("userName", "");
		}
		productMap.put("productList", proHistory);
    	return productMap;
    }
    
    public Map<String, Object> summaryBagDetailsService(Long userId, HttpServletRequest request) {
    	List<ProductDetails> proHistory = new ArrayList<ProductDetails>();
    	updateProductStatus(userId);
    	List<Object[]> cartListObj = shoppingCartRepository.getPurchasedDetails(userId);
    	Map<String, Object> productMap = new HashMap<>();
    	for(int i=0; i<cartListObj.size();i++) {
    		Object[] eCartObj = cartListObj.get(i);
    		BigInteger proId = (BigInteger)eCartObj[0];
    		List<Object[]> proListObj = shoppingCartRepository.getProductDetails(proId);
    		Object[] proDet = proListObj.get(0);
    		
    		ProductDetails  pdObj = new ProductDetails();
    		pdObj.setProId((BigInteger)proDet[0]);
    		pdObj.setProductName((String)proDet[1]);
    		pdObj.setProcDesc((String)proDet[2]);
    		pdObj.setUnitPrice(((Double)proDet[3]));
    		pdObj.setAmmount(((Double)eCartObj[1]));
    		pdObj.setImageUrl((String)proDet[4]);
    		pdObj.setStockDec((BigDecimal)eCartObj[2]);
    		String productDetails = "{\"proId\":\""+proDet[0]+"\",\"name\":\""+proDet[1]+"\",\"desc\":\""+proDet[2]+"\",\"unit_price\":\""+proDet[3]+"\",\"imageUrl\":\""+proDet[4]+"\",\"amount\":\""+eCartObj[1]+"\",\"stock\":\""+eCartObj[2]+"\"}";
    		proHistory.add(pdObj);
    	}
    	HttpSession session = request.getSession();
		if(request.getRemoteUser() != null && request.getRemoteUser() != ""){
			productMap.put("userName", request.getRemoteUser());
		}else{
			productMap.put("userName", "");
		}
		productMap.put("productList", proHistory);
    	return productMap;
    } 
    
    public void deleteProductFromCart(Long product_id) {
    	shoppingCartRepository.deleteProductById(product_id);
    }
    
    public void updateProductStatus(Long userId) {
    	shoppingCartRepository.updateProductStatus(userId);
    }
    
}

