package com.fsse2401.projectBackend.service.impl;

import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import com.fsse2401.projectBackend.data.transaction.entity.TransactionEntity;
import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.projectBackend.repository.TransactionProductRepository;
import com.fsse2401.projectBackend.service.TransactionProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }
    @Override
    public TransactionProductEntity toTransactionProduct(CartItemEntity cartItemEntity, TransactionEntity transactionEntity){
        TransactionProductEntity transactionProduct = new TransactionProductEntity(cartItemEntity, transactionEntity);
        transactionProductRepository.save(transactionProduct);

        return transactionProduct;
    }

    @Override
    public List<TransactionProductEntity> getProductEntityListByTransaction(TransactionEntity transaction){
        return transactionProductRepository.findAllByTransaction(transaction);
    }
}
