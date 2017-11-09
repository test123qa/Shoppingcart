package com.virtualagent.boot.pojo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductDetails {
   private long productId;
   private String userName;
   private String productName;
   private String procDesc;
   private double unitPrice;
   private int quantity;
   private int stock;
   private double ammount;
   private BigInteger proId;
   private BigDecimal stockDec;
   private String imageUrl;
public long getProductId() {
	return productId;
}
public void setProductId(long productId) {
	this.productId = productId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getProcDesc() {
	return procDesc;
}
public void setProcDesc(String procDesc) {
	this.procDesc = procDesc;
}
public double getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(double unitPrice) {
	this.unitPrice = unitPrice;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public double getAmmount() {
	return ammount;
}
public void setAmmount(double ammount) {
	this.ammount = ammount;
}
public BigInteger getProId() {
	return proId;
}
public void setProId(BigInteger proId) {
	this.proId = proId;
}
public BigDecimal getStockDec() {
	return stockDec;
}
public void setStockDec(BigDecimal stockDec) {
	this.stockDec = stockDec;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

}
