package com.fsse2401.projectBackend.service.impl;

import com.fsse2401.projectBackend.data.cartItem.domain.CartItemResponseData;
import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.product.entity.ProductEntity;
import com.fsse2401.projectBackend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.projectBackend.data.user.entity.UserEntity;
import com.fsse2401.projectBackend.exception.cartItem.CartItemNotFoundException;
import com.fsse2401.projectBackend.exception.cartItem.CartItemRemoveException;
import com.fsse2401.projectBackend.exception.cartItem.PutCartItemException;
import com.fsse2401.projectBackend.exception.product.ProductNotFoundException;
import com.fsse2401.projectBackend.repository.CartItemRepository;
import com.fsse2401.projectBackend.service.CartItemService;
import com.fsse2401.projectBackend.service.ProductService;
import com.fsse2401.projectBackend.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    final UserService userService;
    final ProductService productService;
    final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public boolean putCartItem(FirebaseUserData firebaseUserData,
                            Integer pid,
                            Integer quantity){
        try{
            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            Optional<CartItemEntity> cartItemEntity = getByProductAndUser(productEntity,userEntity);
            if (cartItemEntity.isEmpty()){
                if (!productService.isValidQuantity(productEntity,quantity)){
                    throw new PutCartItemException();
                }
                cartItemRepository.save(new CartItemEntity(productEntity,userEntity,quantity));
            } else {
                if (!productService.isValidQuantity(productEntity,quantity)){
                    throw new PutCartItemException();
                }
                cartItemEntity.get().setQuantity(cartItemEntity.get().getQuantity()+quantity);
                cartItemRepository.save(cartItemEntity.get());
            }
            return true;
        } catch (ProductNotFoundException | PutCartItemException ex) {
            logger.info("Put Item Failed.");
            throw ex;
        }

    }

    @Override
    public List<CartItemResponseData> getAllCartItem(FirebaseUserData firebaseUserData){

        List<CartItemResponseData> cartItemResponseDataList = new ArrayList<>();

        for (CartItemEntity entity: cartItemRepository.findAllByUser(userService.getEntityByFirebaseUserData(firebaseUserData))){
            cartItemResponseDataList.add(new CartItemResponseData(entity));
        }
        return cartItemResponseDataList;
    }

    @Override
    public CartItemResponseData updateCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        try {
            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            ProductEntity productEntity = productService.getProductEntityByPid(pid);
            Optional<CartItemEntity> cartItemEntity = getByProductAndUser(productEntity, userEntity);

            if (cartItemEntity.isEmpty()) {
                throw new CartItemNotFoundException();
            }

            if (!productService.isValidQuantity(productEntity, quantity)) {
                throw new PutCartItemException();
            }

            cartItemEntity.get().setQuantity(quantity);
            return new CartItemResponseData(cartItemRepository.save(cartItemEntity.get()));
        }
         catch (CartItemNotFoundException ex){
            logger.warn("Update Cart Item: Cart Item not Found.");
            throw ex;
        } catch (PutCartItemException ex){
            logger.warn("Update Cart Item: Invalid Quantity.");
            throw ex;
        }
    }

    @Override
    @Transactional
    public boolean deleteCartItem(FirebaseUserData firebaseUserData, Integer pid){
//        try {
//            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
//            ProductEntity productEntity = productService.getEntityByPid(pid);
//            Optional<CartItemEntity> cartItemEntity = getByProductAndUser(productEntity, userEntity);
//
//            if (cartItemEntity.isEmpty()) {
//                throw new CartItemNotFoundException();
//            }
//
//            cartItemRepository.delete(cartItemEntity.get());
//            return true;
//        } catch (CartItemNotFoundException ex) {
//            logger.warn("Delete Cart Item: Cart Item not Found.");
//            throw ex;
//        }

        int count = cartItemRepository.deleteByProductPidAndUserFirebaseUid(pid, firebaseUserData.getFirebaseUid());
        if (count<=0){
            CartItemRemoveException ex = new CartItemRemoveException(pid,firebaseUserData.getFirebaseUid());
            logger.warn(ex.getMessage());
            throw(ex);
        }
        return true;
    }

    @Override
    public List<CartItemEntity> getEntityListByUser(UserEntity user){
        return cartItemRepository.findAllByUser(user);
    }

//    @Override
    public Optional<CartItemEntity> getByProductAndUser(ProductEntity product, UserEntity user){
       return cartItemRepository.findByProductAndUser(product, user);
    }

    @Override
    @Transactional
    public void emptyCartItem(UserEntity user){
        cartItemRepository.deleteAllByUser(user);
    }


//    public boolean putCartItem(FirebaseUserData firebaseUserData,
//                               Integer pid, Integer quantity){
//        Optional<CartItemEntity> cartItemEntityOptional = cartItemRepository.findByProductAndUserFirebaseUid(pid, firebaseUserData.getFirebaseUid());
//
//        if (cartItemEntityOptional.isEmpty()){
//            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
//            ProductEntity productEntity = productService.getEntityByPid(pid);
//            if()
//            CartItemEntity cartItemEntity = new CartItemEntity(productEntity, userEntity,quantity);
//            cartItemRepository.save(cartItemEntity);
//        } else {
//            CartItemEntity cartItemEntity = cartItemEntityOptional.get();
//            cartItemEntity.setQuantity();
//        }
//    }

//    public CartItemEntity getEntityByPidAndFirebaseUid (Integer pid, String firebaseUid){
//        return cartItemRepository.findByProductPidAndUserFirebaseUid(pid, firebaseUid)
//                .orElseThrow(() -> new CartItemNotFoundException(pid,firebaseUid));
//    }
}