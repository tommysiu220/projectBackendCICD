package com.fsse2401.projectBackend.data.transactionProduct.entity;

import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.transaction.entity.TransactionEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_product")
public class TransactionProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tpid;
    @ManyToOne
    @JoinColumn(name = "tid")
    private TransactionEntity transaction;
    private Integer pid;
    @Column(nullable=false)
    private String productName;
    @Column(nullable=false)
    private BigDecimal price;
    private String imageUrl;
    private String description;
    @Column(nullable = false)
    private Integer stock;

    private Integer quantity;


    public TransactionProductEntity() {
    }

    public TransactionProductEntity(CartItemEntity entity,TransactionEntity transaction){
        this.transaction = transaction;
        this.pid = entity.getProduct().getPid();
        this.productName = entity.getProduct().getProductName();
        this.description = entity.getProduct().getDescription();
        this.price = entity.getProduct().getPrice();
        this.imageUrl = entity.getProduct().getImageUrl();
        this.stock = entity.getProduct().getStock();
        this.quantity = entity.getQuantity();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transactionEntity) {
        this.transaction = transactionEntity;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
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