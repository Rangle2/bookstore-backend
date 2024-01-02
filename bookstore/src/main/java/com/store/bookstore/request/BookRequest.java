package com.store.bookstore.request;

import lombok.Data;

@Data
public class BookRequest {
    private Long bookId;
    private String author;
    private String name;
    private byte[] picByte;
    private String price;
}
