package com.store.bookstore.controller;

import com.store.bookstore.entity.Role;
import com.store.bookstore.entity.User;
import com.store.bookstore.request.AdminRequest;
import com.store.bookstore.request.UserChangeRequest;
import com.store.bookstore.request.UserRequest;
import com.store.bookstore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
      private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userId/{userId}")
    public User findByUserId(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }


    @GetMapping("/user/{username}/{email}")
    public ResponseEntity<User> findByUsernameAndEmail(
            @PathVariable String username,
            @PathVariable String email){
        User user = userService.getUserByUsernameAndEmail(username, email);
        if (user !=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/role/{username}")
    public Set<Role> findByUserRole(@PathVariable String username){
        return userService.getRolesByUsername(username);
    }



    @GetMapping("/currentUser")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }
    @PostMapping("/login")
    public ResponseEntity<Object> loginAuth(@RequestBody UserRequest userRequest) throws Exception {
        try {
            String token = userService.authUser(userRequest);
            String name = userService.findUserFirstName(userRequest.getUsername());
            String lastname = userService.findUserLastName(userRequest.getUsername());
            Long userId = userService.findUserIdByUsername(userRequest.getUsername());

            if (token != null && userId != null) {
                // If both token and userId are available, return them with ACCEPTED status
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("token", token);
                responseMap.put("userId", userId);
                responseMap.put("firstName", name);
                responseMap.put("lastName", lastname);

                return new ResponseEntity<>(responseMap, HttpStatus.ACCEPTED);
            } else {
                // If username or password is incorrect, return UNAUTHORIZED status
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            // Handle other exceptions (e.g., database errors, etc.)
            return new ResponseEntity<>("Error during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) throws Exception {
        ResponseEntity<User> userValidate = userService.userValidate(userRequest.getUsername(), userRequest.getEmail());
        if (userValidate.getBody() == null) {
            // User not found, allowed to create users
            User createdUser = userService.createUser(userRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            User existingUser = userValidate.getBody();

            boolean usernameMatch = existingUser.getUsername().equals(userRequest.getUsername());
            boolean emailMatch = existingUser.getEmail().equals(userRequest.getEmail());

            if (usernameMatch && emailMatch) {
                return new ResponseEntity<>("Username and email already exist", HttpStatus.CONFLICT);
            } else if (usernameMatch) {
                return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
            } else if (emailMatch) {
                return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
            } else {
                User createdUser = userService.createUser(userRequest);
                return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
            }
        }
    }

    @PutMapping("/edit/{userId}")
    public User editUser(@RequestBody UserChangeRequest userChangeRequest, @PathVariable Long userId){
        return userService.editUserById(userChangeRequest, userId);
    }

    @DeleteMapping("/delete/userId/{userId}")
    public void deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }

    @PostMapping("/4215621356")
    public User createAdmin (@RequestBody AdminRequest adminRequest){
        return userService.createAdminUser(adminRequest);
    }

}
