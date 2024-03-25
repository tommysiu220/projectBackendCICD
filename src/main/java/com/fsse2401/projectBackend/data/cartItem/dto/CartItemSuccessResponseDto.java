package com.fsse2401.projectBackend.data.cartItem.dto;

public class CartItemSuccessResponseDto {
    private String result;

    public CartItemSuccessResponseDto() {
        this.result = "SUCCESS";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
