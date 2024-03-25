package com.fsse2401.projectBackend.repository;

import com.fsse2401.projectBackend.data.transaction.entity.TransactionEntity;
import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity,Integer> {
    List<TransactionProductEntity> findAllByTransaction(TransactionEntity transaction);

}
