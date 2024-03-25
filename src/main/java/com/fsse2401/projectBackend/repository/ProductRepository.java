package com.fsse2401.projectBackend.repository;

import com.fsse2401.projectBackend.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {
    Optional<ProductEntity> findByPid(Integer productId);
}
