package com.store.bookstore.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;
}
