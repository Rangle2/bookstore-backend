package com.store.bookstore.controller;

import com.store.bookstore.entity.Role;
import com.store.bookstore.entity.User;
import com.store.bookstore.permission.RoleName;
import com.store.bookstore.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


}
