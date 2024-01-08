package com.store.bookstore.controller;

import com.store.bookstore.entity.Product;
import com.store.bookstore.request.ProductRequest;
import com.store.bookstore.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{productId}")
    public Product getProductById(@PathVariable Long productId){
       return productService.getProductById(productId);
    }

    @GetMapping("/get")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody ProductRequest productRequest ){
        return productService.createProduct(productRequest);
    }

    @PutMapping("/edit/{productId}")
        public Product editProduct(@RequestBody ProductRequest productRequest, @PathVariable  Long productId){
         return productService.editProduct(productRequest, productId);
        }

    @DeleteMapping("/delete/{productId}")
    public void deleteProductById(@PathVariable long productId){
          productService.deleteProductById(productId);
    }

}
