package com.fsse2401.projectBackend.repository;

import com.fsse2401.projectBackend.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity,Integer> {
    Optional<TransactionEntity> findByTidAndUserFirebaseUid(Integer tid, String firebaseUid);
}
