package com.store.bookstore.request;

import com.store.bookstore.entity.Product;
import lombok.Data;

@Data
public class CategoryRequest {
    private String categoryName;
}
