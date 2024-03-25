package com.fsse2401.projectBackend.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fsse2401.projectBackend.data.transaction.domain.TransactionResponseData;
import com.fsse2401.projectBackend.data.transactionProduct.domain.TransactionProductResponseData;
import com.fsse2401.projectBackend.data.transactionProduct.dto.TransactionProductResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {
    private Integer tid;
    private Integer uid;
    @JsonFormat(pattern="yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime datetime;
    private String status;
    private BigDecimal total;
    private List<TransactionProductResponseDto> items = new ArrayList<>();

    public TransactionResponseDto(TransactionResponseData data) {
        this.tid = data.getTid();
        this.uid = data.getUid();
        this.datetime = data.getDatetime();
        this.status = data.getStatus();
        this.total = data.getTotal();
        setItems(data.getItems());
    }

    public List<TransactionProductResponseDto> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductResponseData> itemsDataList) {
        for(TransactionProductResponseData data:itemsDataList){
            items.add(new TransactionProductResponseDto(data));
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
