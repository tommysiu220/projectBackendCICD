package com.fsse2401.projectBackend.api;

import com.fsse2401.projectBackend.data.product.domain.response.ProductResponseData;
import com.fsse2401.projectBackend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2401.projectBackend.data.product.dto.response.ProductResponseDto;
import com.fsse2401.projectBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")

public class ProductApi {
    final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/public/product")
    public List<GetAllProductResponseDto> getAllProduct(){
        List<GetAllProductResponseDto> productResponseDtoList = new ArrayList<>();

        for (ProductResponseData productResponseData : productService.getAllProduct()){
            productResponseDtoList.add(new GetAllProductResponseDto(productResponseData));
        }

        return productResponseDtoList;
    }


    @GetMapping("/public/product/{pid}")
    public ProductResponseDto getAllProduct(@PathVariable int pid){
        return new ProductResponseDto(
                productService.getProductById(pid)
        );
    }
}
