package com.fsse2401.projectBackend.data.transactionProduct.domain;

import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;


import java.math.BigDecimal;


public class TransactionProductResponseData {
    private int tpid;
    private int tid;
    private int pid;
    private String productName;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private Integer stock;
    private Integer quantity;
    private BigDecimal subTotal;
    private String stripePriceId;

    public String getStripePriceId() {
        return stripePriceId;
    }

    public void setStripePriceId(String stripePriceId) {
        this.stripePriceId = stripePriceId;
    }

    public TransactionProductResponseData(TransactionProductEntity entity){
        this.tpid = entity.getTpid();
        this.tid = entity.getTransaction().getTid();
        this.pid = entity.getPid();
        this.productName = entity.getProductName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imageUrl = entity.getImageUrl();
        this.stock = entity.getStock();
        this.quantity = entity.getQuantity();
        this.stripePriceId = entity.getStripePriceId();
        this.subTotal = price.multiply(BigDecimal.valueOf(quantity));
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}
