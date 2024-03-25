package com.fsse2401.projectBackend.repository;

import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.product.entity.ProductEntity;
import com.fsse2401.projectBackend.data.user.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {

    Optional<CartItemEntity> findByProductAndUser(ProductEntity product, UserEntity user);
//    Optional<CartItemEntity> findByProductAndUserFirebaseUid(P)

    List<CartItemEntity> findAllByUser(UserEntity user);
    int deleteByProductPidAndUserFirebaseUid(Integer pid,String firebaseUid);

    void deleteAllByUser(UserEntity user);
    void deleteAllByUserFirebaseUid(String firebaseUid);

}
