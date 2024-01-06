package com.store.bookstore.controller;
import com.store.bookstore.entity.Product;
import com.store.bookstore.request.CategoryRequest;
import com.store.bookstore.entity.Category;
import com.store.bookstore.service.CategoryService;
import com.store.bookstore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private CategoryService categoryService;

    private ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService) {

        this.categoryService = categoryService;
        this.productService = productService;
    }


    @GetMapping("/{categoryId}")
    public Category getCategoryById(Long categoryId){
        return categoryService.getByCategoryId(categoryId);
    }

    @GetMapping("/get")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> getCategoryProducts(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/create/{productId}")
    public Category createCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long productId){
        return categoryService.createCategory(categoryRequest, productId);
    }

    @PostMapping("/create")
    public Category createCategorySelf(@RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategorySelf(categoryRequest);
    }

    @PutMapping("/edit/{categoryId}/{productId}")
    public Category editCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId, @PathVariable Long productId) {
        return categoryService.editCategory(categoryRequest, categoryId, productId);
    }

    @DeleteMapping("delete/{categoryId}/{productId}")
    public void deleteCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId, @PathVariable Long productId) {
        categoryService.deleteCategory(categoryId, productId);
    }


}
