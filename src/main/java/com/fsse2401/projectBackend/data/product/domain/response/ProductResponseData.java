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
    private String stripePriceId;
    private String imageUrl2;
    private String imageUrl3;

    public ProductResponseData(ProductEntity entity) {
        this.pid = entity.getPid();
        this.productName = entity.getProductName();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImageUrl();
        this.description = entity.getDescription();
        this.stock = entity.getStock();
        this.stripePriceId = entity.getStripePriceId();
        this.imageUrl2 = entity.getImageUrl2();
        this.imageUrl3 = entity.getImageUrl3();
        hasStock = stock > 0;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getStripePriceId() {
        return stripePriceId;
    }

    public void setStripePriceId(String stripePriceId) {
        this.stripePriceId = stripePriceId;
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
