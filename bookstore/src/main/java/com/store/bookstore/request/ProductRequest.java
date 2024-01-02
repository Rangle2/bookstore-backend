package com.store.bookstore.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private double price;
}
