package com.store.bookstore.request;

import lombok.Data;

@Data
public class UserChangeRequest {
    private String username;
    private String Password;
    private String newPassword;
}
