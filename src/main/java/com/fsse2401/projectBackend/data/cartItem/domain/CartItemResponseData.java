package com.fsse2401.projectBackend.data.cartItem.domain;

import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.product.domain.response.ProductResponseData;
import com.fsse2401.projectBackend.data.product.entity.ProductEntity;
import com.fsse2401.projectBackend.data.user.domainObject.UserResponseData;
import com.fsse2401.projectBackend.data.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;

public class CartItemResponseData {
    private Integer cid;
    private ProductResponseData product;
    private UserResponseData user;
    private Integer quantity;

    public CartItemResponseData() {
    }

    public CartItemResponseData(CartItemEntity entity){
        this.cid = entity.getCid();
        this.product = new ProductResponseData(entity.getProduct());
        this.user = new UserResponseData(entity.getUser());
        this.quantity = entity.getQuantity();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductResponseData getProduct() {
        return product;
    }

    public void setProduct(ProductResponseData product) {
        this.product = product;
    }

    public UserResponseData getUser() {
        return user;
    }

    public void setUser(UserResponseData user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
