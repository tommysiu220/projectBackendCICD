package com.fsse2401.projectBackend.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.projectBackend.data.product.domain.response.ProductResponseData;

import java.math.BigDecimal;

public class GetAllProductResponseDto {
    private Integer pid;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    @JsonProperty("has_stock")
    private Boolean hasStock;
//    @JsonIgnore
    private String description;
    @JsonIgnore
    private Integer stock;

    public GetAllProductResponseDto(ProductResponseData data) {
        this.pid = data.getPid();
        this.productName = data.getProductName();
        this.price = data.getPrice();
        this.imageUrl = data.getImageUrl();
        this.description = data.getDescription();
        this.stock = data.getStock();
        hasStock = stock > 0;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
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
