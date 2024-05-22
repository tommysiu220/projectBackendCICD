package com.fsse2401.projectBackend.api;

import com.fsse2401.projectBackend.config.EnvConfig;
import com.fsse2401.projectBackend.data.cartItem.domain.CartItemResponseData;
import com.fsse2401.projectBackend.data.cartItem.dto.CartItemResponseDto;
import com.fsse2401.projectBackend.data.cartItem.dto.CartItemSuccessResponseDto;
import com.fsse2401.projectBackend.service.CartItemService;
import com.fsse2401.projectBackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL, EnvConfig.PROD_S3_BASE_URL,"http://192.168.19.63:8081"})

public class CartItemApi {
    final CartItemService cartItemService;

    @Autowired
    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public CartItemSuccessResponseDto putCartItem(JwtAuthenticationToken jwtToken,
                                                  @PathVariable Integer pid,
                                                  @PathVariable Integer quantity) {
        cartItemService.putCartItem(JwtUtil.getFirebaseUserData(jwtToken) , pid , quantity);
        return new CartItemSuccessResponseDto();
    }

    @GetMapping
    public List<CartItemResponseDto> getAllCartItem(JwtAuthenticationToken jwtToken){

        List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();

        for (CartItemResponseData data : cartItemService.getAllCartItem(JwtUtil.getFirebaseUserData(jwtToken))){
            cartItemResponseDtoList.add(new CartItemResponseDto(data));
        }

        return cartItemResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemResponseDto updateCartItem(JwtAuthenticationToken jwtToken,
                                                  @PathVariable Integer pid,
                                                  @PathVariable Integer quantity) {
        return new CartItemResponseDto(
                cartItemService.updateCartItem(
                        JwtUtil.getFirebaseUserData(jwtToken) , pid , quantity
                )
        );
    }

    @DeleteMapping("/{pid}")
    public CartItemSuccessResponseDto deleteCartItem(JwtAuthenticationToken jwtToken,
                                                     @PathVariable Integer pid){
       cartItemService.deleteCartItem(JwtUtil.getFirebaseUserData(jwtToken),pid);
       return new CartItemSuccessResponseDto();
    }
}
