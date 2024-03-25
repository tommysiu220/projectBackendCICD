package com.fsse2401.projectBackend.data.transaction.dto;

public class TransactionSuccessResponseDto {
    private String status;

    public TransactionSuccessResponseDto(){
        this.status = "SUCCESS";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
