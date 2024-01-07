package com.store.bookstore.service;

import com.store.bookstore.entity.Role;
import com.store.bookstore.entity.User;
import com.store.bookstore.permission.RoleName;
import com.store.bookstore.repos.RoleRepository;
import com.store.bookstore.repos.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {

        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    public Role getRoleName(RoleName roleName) {
        return roleRepository.findByName(roleName).orElse(null);
    }

    @Transactional
    public Role changeRole(User user, Role newRole) {

        user.getRoles().clear();
        user.getRoles().add(newRole);

        roleRepository.save(newRole);
        userRepository.save(user);
        return newRole;

    }
}
