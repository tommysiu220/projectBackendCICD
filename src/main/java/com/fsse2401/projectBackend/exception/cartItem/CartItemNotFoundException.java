package com.fsse2401.projectBackend.exception.cartItem;

import com.fsse2401.projectBackend.data.cartItem.entity.CartItemEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException(){

    }
    public CartItemNotFoundException(Integer pid, String firebaseUid){
        super(String.format("CartItem Not Foud:%d, %s",pid,firebaseUid));
    }
}
