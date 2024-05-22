package com.fsse2401.projectBackend.data.transaction.domain;

import com.fsse2401.projectBackend.data.transaction.entity.TransactionEntity;
import com.fsse2401.projectBackend.data.transactionProduct.domain.TransactionProductResponseData;
import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseData {
    private Integer tid;
    private Integer uid;
    private LocalDateTime datetime;
    private String status;
    private BigDecimal total;
    private String priceId;
    private List<TransactionProductResponseData> items = new ArrayList<>();

    public TransactionResponseData(TransactionEntity entity, List<TransactionProductEntity> transactionProductEntityList) {
        this.tid = entity.getTid();
        this.uid = entity.getUser().getUid();
        this.datetime = entity.getDatetime();
        this.status = entity.getStatus();
        this.total = entity.getTotal();
//        this.priceId = entity.
        setItems(transactionProductEntityList);
    }

    public List<TransactionProductResponseData> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductEntity> transactionProductEntityList) {
        for(TransactionProductEntity entity:transactionProductEntityList){
            items.add(new TransactionProductResponseData(entity));
        }
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
