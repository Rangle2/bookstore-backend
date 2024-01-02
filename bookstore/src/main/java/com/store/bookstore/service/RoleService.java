package com.store.bookstore.service;

import com.store.bookstore.entity.Role;
import com.store.bookstore.permission.RoleName;
import com.store.bookstore.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleName(RoleName roleName){
        return roleRepository.findByName(roleName).orElse(null);
    }


}
