package com.fsse2401.projectBackend.service.impl;

import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.transaction.domain.TransactionResponseData;
import com.fsse2401.projectBackend.data.transaction.entity.TransactionEntity;
import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.projectBackend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.projectBackend.data.user.entity.UserEntity;
import com.fsse2401.projectBackend.exception.product.InvalidProductQuantityException;
import com.fsse2401.projectBackend.exception.transaction.CartItemExceedException;
import com.fsse2401.projectBackend.exception.transaction.EmptyCartException;
import com.fsse2401.projectBackend.exception.transaction.InvalidTransactionStatusException;
import com.fsse2401.projectBackend.exception.transaction.TransactionNotFoundException;
import com.fsse2401.projectBackend.repository.TransactionRepository;
import com.fsse2401.projectBackend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    private final TransactionRepository transactionRepository;
    private final ProductService productService;

    @Autowired
    public TransactionServiceImpl(UserService userService,
                                  CartItemService cartItemService,
                                  TransactionProductService transactionProductService,
                                  TransactionRepository transactionRepository,
                                  ProductService productService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
        this.transactionRepository = transactionRepository;
        this.productService = productService;
    }

    @Override
    public TransactionResponseData createNewTransaction(FirebaseUserData firebaseUserData) {
        try {
            UserEntity user = userService.getEntityByFirebaseUserData(firebaseUserData);
            List<CartItemEntity> cartItemEntityList = cartItemService.getEntityListByUser(
                    user);
            if (cartItemEntityList.isEmpty()) {
                throw new EmptyCartException();
            }


            TransactionEntity transactionEntity = new TransactionEntity(user);
            transactionRepository.save(transactionEntity);

            BigDecimal total = new BigDecimal(0);

            List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();
            for (CartItemEntity cartItemEntity : cartItemEntityList) {
                if (cartItemEntity.getQuantity() < cartItemEntity.getProduct().getStock()) {
                    transactionProductEntityList.add(transactionProductService.toTransactionProduct(cartItemEntity, transactionEntity));
                    total = total.add(cartItemEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity())));
                } else throw new CartItemExceedException();
            }

            transactionEntity.setTotal(total);
            transactionRepository.save(transactionEntity);

            return new TransactionResponseData(transactionEntity, transactionProductEntityList);

        } catch (EmptyCartException ex) {
            logger.warn("Create Transaction Error. Empty Shopping Cart.");
            throw ex;
        } catch (CartItemExceedException ex) {
            logger.warn("Create Transaction Error. Cart item exceeded.");
            throw ex;
        }
    }

    @Override
    public TransactionResponseData getTransactionById(FirebaseUserData firebaseUserData, Integer tid) {
        try {
            TransactionEntity transactionEntity = getTransactionEntityByTidAndFirebaseUid(tid, firebaseUserData.getFirebaseUid());

            List<TransactionProductEntity> transactionProductEntityList =
                    transactionProductService.getProductEntityListByTransaction(transactionEntity);

            return new TransactionResponseData(transactionEntity, transactionProductEntityList);

        } catch (TransactionNotFoundException ex) {
            logger.warn("Get Transaction Error. " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void payTransaction(FirebaseUserData firebaseUserData, Integer tid) {
        try {
            TransactionEntity transactionEntity = getTransactionEntityByTidAndFirebaseUid(tid, firebaseUserData.getFirebaseUid());

            List<TransactionProductEntity> transactionProductEntityList =
                    transactionProductService.getProductEntityListByTransaction(transactionEntity);

            if (!transactionEntity.getStatus().equals("PREPARE")) {
                throw new InvalidTransactionStatusException();
            }

            for (TransactionProductEntity transactionProductEntity : transactionProductEntityList) {
                if (!productService.isValidQuantity(transactionProductEntity.getPid(), transactionProductEntity.getQuantity())) {
                    throw new InvalidProductQuantityException(transactionProductEntity.getPid());
                }
            }

            for (TransactionProductEntity transactionProductEntity : transactionProductEntityList) {
                productService.deductStock(transactionProductEntity.getPid(), transactionProductEntity.getQuantity());
            }

            transactionEntity.setStatus("PROCESSING");
            transactionRepository.save(transactionEntity);

        } catch (InvalidProductQuantityException ex) {
            logger.warn("Pay Transaction Error. " + ex.getMessage());
            throw ex;
        } catch (InvalidTransactionStatusException ex) {
            logger.warn("Pay Transaction Error. Invalid Transaction Status.");
            throw ex;
        }
    }

    @Override
    public TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid) {
        try {
            UserEntity user = userService.getEntityByFirebaseUserData(firebaseUserData);
            TransactionEntity transactionEntity = getTransactionEntityByTidAndFirebaseUid(tid, user.getFirebaseUid());

            if (!transactionEntity.getStatus().equals("PROCESSING")) {
                throw new InvalidTransactionStatusException();
            }

            cartItemService.emptyCartItem(user);

            transactionEntity.setStatus("SUCCESS");
            transactionRepository.save(transactionEntity);

            return new TransactionResponseData(transactionEntity,
                    transactionProductService.getProductEntityListByTransaction(transactionEntity));

        } catch (InvalidTransactionStatusException ex) {
            logger.warn("Finish Transaction Error. Invalid Transaction Status.");
            throw ex;
        }
    }

    public TransactionEntity getTransactionEntityByTidAndFirebaseUid(Integer tid, String firebaseUid) {
        return transactionRepository.findByTidAndUserFirebaseUid(tid, firebaseUid).orElseThrow(
                () -> new TransactionNotFoundException(tid, firebaseUid));
    }
}
