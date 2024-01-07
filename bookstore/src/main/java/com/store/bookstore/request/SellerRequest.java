package com.store.bookstore.request;

import lombok.Data;

@Data
public class SellerRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String companyName;
    private Long phoneNumber;
}
