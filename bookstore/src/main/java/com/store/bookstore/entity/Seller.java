package com.store.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long sellerId;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastname;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "seller_roles",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
