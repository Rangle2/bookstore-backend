package com.store.bookstore.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartRequest {
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private Long productId;
    private String productImg;
}
