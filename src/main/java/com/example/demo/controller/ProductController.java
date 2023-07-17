package com.example.demo.controller;


import com.example.demo.data.dto.ProductDto;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired //의존성 주입
    public ProductController(ProductService productService) {

        this.productService = productService;

    }

    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId){
        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of Around Hub API.", "getProduct");

        ProductDto productDto = productService.getProduct(productId);

        LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms",
                productDto.getProductId(),productDto.getProductName(),productDto.getProductPrice(),productDto.getProductStock(),
                (System.currentTimeMillis() - startTime));
        return productService.getProduct(productId);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){

        if(productDto.getProductId().equals("") || productDto.getProductId().isEmpty()){
            LOGGER.error("[createProduct] failed Response :: productId is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }


        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        ProductDto response = productService.saveProduct(productId, productName, productPrice, productStock);

        LOGGER.info(
                "[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
                response.getProductId(), response.getProductName(), response.getProductPrice(),
                response.getProductStock());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {

        return  productService.deleteProduct(productId);
    }

}
