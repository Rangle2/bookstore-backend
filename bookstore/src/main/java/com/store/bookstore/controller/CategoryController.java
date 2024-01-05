package com.store.bookstore.controller;
import com.store.bookstore.request.CategoryRequest;
import com.store.bookstore.entity.Category;
import com.store.bookstore.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/{categoryId}")
    public Category getCategoryById(Long categoryId){
        return categoryService.getByCategoryId(categoryId);
    }

    @GetMapping("/get")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long productId){
        return categoryService.createCategory(categoryRequest, productId);
    }

    @PutMapping("/edit/{categoryId}")
    public Category editCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId, @PathVariable Long productId) {
        return categoryService.editCategory(categoryRequest, categoryId, productId);
    }

    @DeleteMapping("delete/{categoryId}")
    public void deleteCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId, @PathVariable Long productId) {
        categoryService.deleteCategory(categoryId, productId);
    }


}
