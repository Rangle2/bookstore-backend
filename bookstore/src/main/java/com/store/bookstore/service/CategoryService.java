package com.store.bookstore.service;

import com.store.bookstore.entity.Category;
import com.store.bookstore.entity.Product;
import com.store.bookstore.repos.CategoryRepository;
import com.store.bookstore.repos.ProductRepository;
import com.store.bookstore.request.CategoryRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    @Resource
    private ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category getByCategoryId(Long categoryId) {
        return categoryRepository.getByCategoryId(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public Category createCategory(CategoryRequest categoryRequest, Long productId) {

        Category existingCategory = categoryRepository.getByCategoryName(categoryRequest.getCategoryName());

        if (existingCategory == null) {
            existingCategory = new Category();
            existingCategory.setCategoryName(categoryRequest.getCategoryName());
            categoryRepository.save(existingCategory);
        }


        Product product = productRepository.findById(productId).orElse(null);
        product.setCategory(existingCategory);
        productRepository.save(product);


        return existingCategory;

    }

    public Category editCategory(CategoryRequest categoryRequest, Long categoryId, Long productId) {
        Category existingCategory = categoryRepository.getByCategoryId(categoryId);

        if (existingCategory != null) {
            existingCategory.setCategoryName(categoryRequest.getCategoryName());
            categoryRepository.save(existingCategory);

            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                product.setCategory(existingCategory);  // Update Product Category
                productRepository.save(product);
            }
        }

        return existingCategory;
    }

    public void deleteCategory(Long categoryId, Long productId) {
        categoryRepository.deleteById(categoryId);
    }

    public Category createCategorySelf(CategoryRequest categoryRequest) {
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryRequest.getCategoryName());
        return categoryRepository.save(newCategory);
    }
}

