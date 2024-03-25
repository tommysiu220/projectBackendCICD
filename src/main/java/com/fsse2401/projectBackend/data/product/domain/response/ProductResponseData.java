package com.fsse2401.projectBackend.data.product.domain.response;

import com.fsse2401.projectBackend.data.product.entity.ProductEntity;

import java.math.BigDecimal;

public class ProductResponseData {

    private int pid;
    private String productName;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private Integer stock;
    private Boolean hasStock;

    public ProductResponseData(ProductEntity entity) {
        this.pid = entity.getPid();
        this.productName = entity.getProductName();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImageUrl();
        this.description = entity.getDescription();
        this.stock = entity.getStock();
        hasStock = stock > 0;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }
}
