package com.store.bookstore.request;

import lombok.Data;

@Data
public class AdminRequest {
    private String username;
    private String password;
    private String avatarFileName;
}
