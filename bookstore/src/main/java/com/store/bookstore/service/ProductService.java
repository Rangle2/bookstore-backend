package com.store.bookstore.service;

import com.store.bookstore.entity.Book;
import com.store.bookstore.entity.Product;
import com.store.bookstore.repos.ProductRepository;
import com.store.bookstore.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
      private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long productId) {
       return productRepository.getByProductId(productId);
    }


    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }


    public Product createProduct(ProductRequest productRequest) {
        Product newProduct = new Product();
        newProduct.setName(productRequest.getName());
        newProduct.setDescription(productRequest.getDescription());
        newProduct.setPrice(productRequest.getPrice());
        newProduct.setImgLink(productRequest.getImgLink());
        return productRepository.save(newProduct);
    }

    public Product editProduct(ProductRequest productRequest, Long productId){
        Product existingProduct = productRepository.getByProductId(productId);
        if (existingProduct != null){
            existingProduct.setName(productRequest.getName());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setImgLink(productRequest.getImgLink());
            return productRepository.save(existingProduct);
        }else {
            return null;
        }
    }

    public void deleteProductById(long productId) {
        Product existingProduct = productRepository.getByProductId(productId);
        if (existingProduct != null){
           productRepository.deleteById(productId);
        }else {
            /* Exception */
        }
    }

    public List<Product> getProductByCategoryId(Long categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }
}
