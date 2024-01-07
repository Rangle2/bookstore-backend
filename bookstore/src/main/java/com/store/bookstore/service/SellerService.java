package com.store.bookstore.service;

import com.store.bookstore.entity.Role;
import com.store.bookstore.entity.Seller;
import com.store.bookstore.entity.User;
import com.store.bookstore.permission.RoleName;
import com.store.bookstore.repos.SellerRepository;
import com.store.bookstore.repos.UserRepository;
import com.store.bookstore.request.SellerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    private SellerRepository sellerRepository;

    private UserService userService;

    private UserRepository userRepository;

    private RoleService roleService;

    public SellerService(SellerRepository sellerRepository, UserRepository userRepository,RoleService roleService, UserService userService) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userService = userService;
    }

    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }

    public Seller createSeller(SellerRequest sellerRequest) {
        // get user info
        User existUser = userRepository.findByUsername(sellerRequest.getUsername());
        Role newRole = roleService.getRoleName(RoleName.ROLE_SELLER);
        existUser.getRoles().add(newRole);
        userRepository.save(existUser);


        // Seller entity request
        Seller newSeller = new Seller();
        newSeller.setFirstName(sellerRequest.getFirstName());
        newSeller.setLastName(sellerRequest.getLastName());
        newSeller.setCompanyName(sellerRequest.getCompanyName());

        sellerRepository.save(newSeller);



        return newSeller;
    }
}
