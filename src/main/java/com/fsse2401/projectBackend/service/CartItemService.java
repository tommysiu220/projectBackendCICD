package com.fsse2401.projectBackend.service;

import com.fsse2401.projectBackend.data.cartItem.domain.CartItemResponseData;
import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.projectBackend.data.user.entity.UserEntity;

import java.util.List;

public interface CartItemService {
    boolean putCartItem(FirebaseUserData firebaseUserData,
                     Integer pid,
                     Integer quantity);

    List<CartItemResponseData> getAllCartItem(FirebaseUserData firebaseUserData);

    CartItemResponseData updateCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    boolean deleteCartItem(FirebaseUserData firebaseUserData, Integer pid);

    List<CartItemEntity> getEntityListByUser(UserEntity userEntity);

    void emptyCartItem(UserEntity user);
}
