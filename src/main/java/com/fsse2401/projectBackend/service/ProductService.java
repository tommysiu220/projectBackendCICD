package com.fsse2401.projectBackend.service;

import com.fsse2401.projectBackend.data.product.domain.response.ProductResponseData;
import com.fsse2401.projectBackend.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductResponseData> getAllProduct();

    ProductResponseData getProductById(int productId);

    ProductEntity getProductEntityByPid(int productId);

    boolean isValidQuantity(ProductEntity entity, Integer quantity);

    boolean isValidQuantity(Integer pid, Integer quantity);

    void deductStock(Integer pid, Integer stock);
}
