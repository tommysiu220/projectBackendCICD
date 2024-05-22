package com.fsse2401.projectBackend.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.projectBackend.data.product.domain.response.ProductResponseData;

import java.math.BigDecimal;

public class ProductResponseDto {
    private int pid;
    @JsonProperty("product_name")
    private String productName;
    private String description;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    @JsonProperty("stripe_price_id")
    private String stripePriceId;

    public String getStripePriceId() {
        return stripePriceId;
    }

    public void setStripePriceId(String stripePriceId) {
        this.stripePriceId = stripePriceId;
    }

    public ProductResponseDto(ProductResponseData data) {
        this.pid = data.getPid();
        this.productName = data.getProductName();
        this.price = data.getPrice();
        this.imageUrl = data.getImageUrl();
        this.description = data.getDescription();
        this.stock = data.getStock();
        this.stripePriceId = data.getStripePriceId();
    }

    public ProductResponseDto(int pid,String productName,BigDecimal price,String imageUrl,String description,Integer stock, String stripePriceId){
        this.pid = pid;
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.stock = stock;
        this.stripePriceId = stripePriceId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
