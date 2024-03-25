package com.fsse2401.projectBackend.service;

import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.transaction.entity.TransactionEntity;
import com.fsse2401.projectBackend.data.transactionProduct.domain.TransactionProductResponseData;
import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;

import java.util.List;

public interface TransactionProductService {
    TransactionProductEntity toTransactionProduct(CartItemEntity cartItemEntity, TransactionEntity transactionEntity);

    List<TransactionProductEntity> getProductEntityListByTransaction(TransactionEntity transaction);
}
