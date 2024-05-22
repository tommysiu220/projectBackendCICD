package com.fsse2401.projectBackend.api;

import com.fsse2401.projectBackend.config.EnvConfig;
import com.fsse2401.projectBackend.data.transaction.dto.TransactionResponseDto;
import com.fsse2401.projectBackend.data.transaction.dto.TransactionSuccessResponseDto;
import com.fsse2401.projectBackend.data.transactionProduct.domain.TransactionProductResponseData;
import com.fsse2401.projectBackend.data.transactionProduct.dto.TransactionProductResponseDto;
import com.fsse2401.projectBackend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.projectBackend.service.TransactionService;
import com.fsse2401.projectBackend.util.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL, EnvConfig.PROD_S3_BASE_URL})
public class TransactionApi {
    final TransactionService transactionService;

    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto createNewTransaction(JwtAuthenticationToken jwtToken){
       return new TransactionResponseDto(transactionService.createNewTransaction(JwtUtil.getFirebaseUserData(jwtToken)));
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionById(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new TransactionResponseDto(transactionService.getTransactionById(JwtUtil.getFirebaseUserData(jwtToken), tid));
    }

    @PatchMapping("/{tid}/pay")
    public TransactionSuccessResponseDto checkoutTransaction(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        transactionService.payTransaction(JwtUtil.getFirebaseUserData(jwtToken), tid);
        return new TransactionSuccessResponseDto();
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto finishTransaction(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new TransactionResponseDto(transactionService.finishTransaction(JwtUtil.getFirebaseUserData(jwtToken), tid));
    }
}
