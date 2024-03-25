package com.fsse2401.projectBackend.service.impl;

import com.fsse2401.projectBackend.data.product.domain.response.ProductResponseData;
import com.fsse2401.projectBackend.data.product.entity.ProductEntity;
import com.fsse2401.projectBackend.exception.product.ProductNotFoundException;
import com.fsse2401.projectBackend.service.ProductService;
import com.fsse2401.projectBackend.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseData> getAllProduct(){
        List<ProductResponseData> productResponseDataList = new ArrayList<>();
        for (ProductEntity entity:productRepository.findAll()){
            productResponseDataList.add(new ProductResponseData(entity));
        }
        return productResponseDataList;
    }

    @Override
    public ProductResponseData getProductById(int productId){
        try{
            return new ProductResponseData(getProductEntityByPid(productId));
        } catch (ProductNotFoundException ex){
            logger.info("Get Product by ID: Product Not Found");
            throw ex;
        }
    }

    @Override
    public ProductEntity getProductEntityByPid(int productId){
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);

        // Check optional object is null or not
        return productEntityOptional.orElseThrow(ProductNotFoundException::new);

        //        Optional<ProductEntity> productEntityOptional = productRepository.findByPid(productId);
//
//        // Check optional object is null or not
//        if (productEntityOptional.isEmpty()) {
//            return null;
//        }
//
//        return productEntityOptional.get();
    }

    @Override
    public boolean isValidQuantity(ProductEntity entity, Integer quantity){
        if (quantity < 1){
            return false;
        } else if (quantity > entity.getStock()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isValidQuantity(Integer pid, Integer quantity){
        ProductEntity entity = getProductEntityByPid(pid);
        if (quantity < 1){
            return false;
        } else if (quantity > entity.getStock()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void deductStock(Integer pid, Integer stock){
        getProductEntityByPid(pid).setStock(getProductEntityByPid(pid).getStock() - stock);
        productRepository.save(getProductEntityByPid(pid));
    }
}
