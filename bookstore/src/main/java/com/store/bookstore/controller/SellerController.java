package com.store.bookstore.controller;

import com.store.bookstore.entity.Role;
import com.store.bookstore.entity.Seller;
import com.store.bookstore.entity.User;
import com.store.bookstore.permission.RoleName;
import com.store.bookstore.request.SellerRequest;
import com.store.bookstore.service.RoleService;
import com.store.bookstore.service.SellerService;
import com.store.bookstore.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/seller")
public class SellerController {

    private SellerService sellerService;



    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;


    }

    @GetMapping("/get")
    public List<Seller> getAllSellers(){
        return sellerService.getAllSellers();
    }

    @PostMapping("/create")
    public Seller createSeller(@RequestBody SellerRequest sellerRequest) {
        return sellerService.createSeller(sellerRequest);
    }
}
