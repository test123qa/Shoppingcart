package com.sahan.zaizi.service;

import com.sahan.zaizi.domain.Product;
import com.sahan.zaizi.domain.ShoppingCart;
import com.sahan.zaizi.dto.ShoppingCartDTO;
import com.sahan.zaizi.pojo.ProductDetails;
import com.sahan.zaizi.repository.ProductRepository;
import com.sahan.zaizi.repository.ShoppingCartRepository;
import com.sahan.zaizi.repository.UserRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sahan.zaizi.service.ProductService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    private ShoppingCartRepository shoppingCartRepository;
    
    @Autowired
    private ProductService productService;
    
    Logger logger = Logger.getLogger(ShoppingCartService.class);

    public ShoppingCart saveProducts(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = productRepository.findOne(shoppingCartDTO.getProductId());
        shoppingCart.setProduct(product);
        shoppingCart.setUser(userRepository.findOne(1L));
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
    
    public String showMyBag(Long productId, Long UserId) {
    	List<Object[]> bagDataList = shoppingCartRepository.showMyBag(UserId, productId);
    	Object[] bagData = bagDataList.get(0);
    	
    	String str = "{\"productDesc\" : \""+bagData[0]+"\", \"productAmount\" : \""+bagData[1]+"\", \"productCount\" : \""+bagData[2]+"\", \"totalProductCount\" : \""+bagData[3]+"\", \"totalAmount\" : \""+bagData[4]+"\"}";
    	return str;
    }
    
    public List<ProductDetails> checkOutBagDetailsService(Long UserId) {
    	List<ProductDetails> proHistory = new ArrayList<ProductDetails>();
    	List<Object[]> cartListObj = shoppingCartRepository.getCartDetails(UserId);
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
    		pdObj.setStockDec((BigDecimal)eCartObj[2]);
    		String productDetails = "{\"proId\":\""+proDet[0]+"\",\"name\":\""+proDet[1]+"\",\"desc\":\""+proDet[2]+"\",\"unit_price\":\""+proDet[3]+"\",\"amount\":\""+eCartObj[1]+"\",\"stock\":\""+eCartObj[2]+"\"}";
    		proHistory.add(pdObj);
    	}
    	return proHistory;
    }
    
    public List<ProductDetails> summaryBagDetailsService(Long userId) {
    	List<ProductDetails> proHistory = new ArrayList<ProductDetails>();
    	List<Object[]> cartListObj = shoppingCartRepository.getPurchasedDetails(userId);
    	updateProductStatus(userId);
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
    		pdObj.setStockDec((BigDecimal)eCartObj[2]);
    		String productDetails = "{\"proId\":\""+proDet[0]+"\",\"name\":\""+proDet[1]+"\",\"desc\":\""+proDet[2]+"\",\"unit_price\":\""+proDet[3]+"\",\"amount\":\""+eCartObj[1]+"\",\"stock\":\""+eCartObj[2]+"\"}";
    		proHistory.add(pdObj);
    	}
    	return proHistory;
    } 
    
    public void deleteProductFromCart(Long product_id) {
    	shoppingCartRepository.deleteProductById(product_id);
    }
    
    public void updateProductStatus(Long userId) {
    	shoppingCartRepository.updateProductStatus(userId);
    }
    
}

