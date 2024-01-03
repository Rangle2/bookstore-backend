package com.store.bookstore.service;

import com.store.bookstore.entity.Role;
import com.store.bookstore.entity.User;
import com.store.bookstore.permission.RoleName;
import com.store.bookstore.repos.RoleRepository;
import com.store.bookstore.repos.UserRepository;
import com.store.bookstore.request.AdminRequest;
import com.store.bookstore.request.UserChangeRequest;
import com.store.bookstore.request.UserRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private final PasswordService passwordService;
    private RoleService roleService;

    @Autowired
    private JwtService jwtService;

    private RoleRepository roleRepository;






    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordService passwordService,
                       RoleService roleService, RoleRepository roleRepository){

        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }


    /* private AvatarService avatarService; */

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            return userRepository.findByUsername(username);
        }

        return null;
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getUserByUsernameAndEmail(String username, String email){
        return userRepository.findByUsernameAndEmail(username, email);
    }

    @Transactional
    public User createUser(UserRequest userRequest) throws Exception {

            User newUser = new User();
            newUser.setUsername(userRequest.getUsername());
            newUser.setEmail(userRequest.getEmail());
            String encodedPass = passwordService.encodePassword(userRequest.getPassword());
            newUser.setPassword(encodedPass);


            Role newRole = roleService.getRoleName(RoleName.ROLE_USER);
            newUser.getRoles().add(newRole);

            userRepository.save(newUser);
            return newUser;


    }

    public User createAdminUser(AdminRequest adminRequest){
        User newAdminUser = new User();

        newAdminUser.setUsername(adminRequest.getUsername());
        String encodedPass = passwordService.encodePassword(adminRequest.getPassword());
        newAdminUser.setPassword(encodedPass);

        Role newRole = roleService.getRoleName(RoleName.ROLE_ADMIN);
        newAdminUser.getRoles().add(newRole);

        userRepository.save(newAdminUser);
        return newAdminUser;
    }

    @Transactional
    public String authUser(UserRequest userRequest) throws Exception {
        // Find User by username in database
        User existingUser = userRepository.findByUsername(userRequest.getUsername());

        if (existingUser != null && passwordService.matchPassword(userRequest.getPassword(), existingUser.getPassword())) {
            // User found and password correct
            return jwtService.generateToken(existingUser.getUsername());
        } else {
            // User not found or password incorrect
            return null;

        }
    }

    @Transactional
    public User editUserById(UserChangeRequest userChangeRequest, Long userId) {
        User existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(userChangeRequest.getUsername());

            String rawPassword = userChangeRequest.getPassword();
            String encodedPassword = existingUser.getPassword();


            if (passwordService.matchPassword(rawPassword, encodedPassword) && userChangeRequest.getNewPassword() != null) {
                String encodedNewPass = passwordService.encodePassword(userChangeRequest.getNewPassword());
                existingUser.setPassword(encodedNewPass);
                userRepository.save(existingUser);
            }
        } return existingUser;

    }
    @Transactional
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }

    public ResponseEntity<User> userValidate(String username, String email) {
       User checkedUser = getUserByUsernameAndEmail(username, email);
       if (checkedUser != null) {
           return new ResponseEntity<>(checkedUser, HttpStatus.OK);
       }else {
           return new ResponseEntity<>(checkedUser, HttpStatus.NOT_FOUND);
       }
    }
}

