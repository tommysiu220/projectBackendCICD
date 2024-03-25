package com.fsse2401.projectBackend.data.transactionProduct.dto;

import com.fsse2401.projectBackend.data.product.domain.response.ProductResponseData;
import com.fsse2401.projectBackend.data.product.dto.response.ProductResponseDto;
import com.fsse2401.projectBackend.data.transactionProduct.domain.TransactionProductResponseData;
import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;

public class TransactionProductResponseDto {
    private int tpid;
    private ProductResponseDto product;

    private Integer quantity;
    private BigDecimal subTotal;

    public TransactionProductResponseDto(TransactionProductResponseData data){
        this.tpid = data.getTpid();

        this.product = new ProductResponseDto(data.getPid(),
                                              data.getProductName(),
                                              data.getPrice(),
                                              data.getImageUrl(),
                                              data.getDescription(),
                                              data.getStock());
        this.subTotal = data.getSubTotal();
        this.quantity = data.getQuantity();
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

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

}
