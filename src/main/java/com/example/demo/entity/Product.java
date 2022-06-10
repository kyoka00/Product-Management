package com.example.demo.entity;

import java.time.LocalDate;

public class Product {
	private Integer productId;
	private String productName;
	private String brandName;
	private Integer categoryId;
	private String categoryName;
	private LocalDate purchaseDate;
	private LocalDate startingDate;
	private LocalDate expirationDate;
	private boolean favorite;
	private boolean finished;
	

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public LocalDate getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public Product() {
		
	}
	public Product(String productName, String brandName, Integer categoryId, LocalDate purchaseDate, LocalDate startingDate, LocalDate expirationDate, boolean favorite, boolean finished) {
		this.productName=productName;
		this.brandName = brandName;
		this.categoryId = categoryId;
		this.purchaseDate = purchaseDate;
		this.startingDate = startingDate;
		this.expirationDate = expirationDate;
		this.favorite = favorite;
		this.finished = finished;
		
	}
	public Product(Integer productId,String productName, String brandName, Integer categoryId, LocalDate purchaseDate, LocalDate startingDate, LocalDate expirationDate, boolean favorite, boolean finished) {
		this.productId = productId;
		this.productName=productName;
		this.brandName = brandName;
		this.categoryId = categoryId;
		this.purchaseDate = purchaseDate;
		this.startingDate = startingDate;
		this.expirationDate = expirationDate;
		this.favorite = favorite;
		this.finished = finished;
		
	}
}
