package com.fsse2401.projectBackend.service;

import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.transaction.domain.TransactionResponseData;
import com.fsse2401.projectBackend.data.transactionProduct.domain.TransactionProductResponseData;
import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.projectBackend.data.user.domainObject.FirebaseUserData;

import java.util.List;

public interface TransactionService {
    TransactionResponseData createNewTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionById(FirebaseUserData firebaseUserData, Integer tid);

    void payTransaction(FirebaseUserData firebaseUserData, Integer tid);

    TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid);
}
