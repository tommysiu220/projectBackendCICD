package com.fsse2401.projectBackend.api;

import com.fsse2401.projectBackend.config.EnvConfig;
import com.fsse2401.projectBackend.data.transaction.dto.TransactionResponseDto;
import com.fsse2401.projectBackend.data.transactionProduct.dto.TransactionProductResponseDto;
import com.fsse2401.projectBackend.service.TransactionService;
import com.fsse2401.projectBackend.util.JwtUtil;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.stripe.Stripe;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL, EnvConfig.PROD_S3_BASE_URL})
public class CheckoutApi {
    final TransactionService transactionService;

    public CheckoutApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/checkout/{tid}")
    public String checkout(JwtAuthenticationToken jwtToken, @PathVariable Integer tid) throws StripeException {
        Stripe.apiKey = "sk_test_51PBpbnRpBm2YYBNuC12sIyKYN0c4zErK8k6K0zTRLpsNXcX384Pje11dmu428OAwoXGEZQwr3U6Xp30Ra5t72fXj00op8HkQDL";
        List<TransactionProductResponseDto> transactionProductResponseDtoList =
                new TransactionResponseDto(transactionService.getTransactionById(JwtUtil.getFirebaseUserData(jwtToken),tid)).getItems();

        String YOUR_DOMAIN = "http://localhost:5173";
//
//        SessionCreateParams params =
//                SessionCreateParams.builder()
//                        .setMode(SessionCreateParams.Mode.PAYMENT)
//                        .setSuccessUrl(YOUR_DOMAIN + "/thankyou")
//                        .setCancelUrl(YOUR_DOMAIN + "/error")
//
//                        .addLineItem(
//                                SessionCreateParams.LineItem.builder()
//                                        .setQuantity(1L)
//                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
//                                        .setPrice("price_1PBqUURpBm2YYBNuCSeqScCj")
//                                        .build())
//                        .addLineItem(
//                                SessionCreateParams.LineItem.builder()
//                                        .setQuantity(1L)
//                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
//                                        .setPrice("price_1PCz6cRpBm2YYBNu5WxeYq0H")
//                                        .build())
//                        .build();
//
//
////        for (TransactionProductResponseDto product : transactionProductResponseDtoList) {
////            SessionCreateParams.builder()
////                    .addLineItem(
////                            SessionCreateParams.LineItem.builder()
////                                    .setQuantity((long) product.getQuantity())
////                                    .setPrice(product.getProduct().getStripePriceId())  // Use the actual Price ID (SKU) from Stripe
////                                    .build()
////                    );
////        }
//
//        Session session = Session.create(params);

        SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(YOUR_DOMAIN + "/thankyou/"+tid)
                .setCancelUrl(YOUR_DOMAIN + "/error");


// Add line items for each product in the productList using a loop
        for (TransactionProductResponseDto product : transactionProductResponseDtoList) {
            paramsBuilder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity((long)product.getQuantity())
                            .setPrice(product.getProduct().getStripePriceId())
                            .build()
            );
        }

        SessionCreateParams params = paramsBuilder.build();
        Session session = Session.create(params);


        System.out.println(session.getUrl());
        return session.getUrl();
    }
}
