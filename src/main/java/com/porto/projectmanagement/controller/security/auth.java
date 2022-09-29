package com.porto.projectmanagement.controller.security;

import com.porto.projectmanagement.model.security.RoleEntity;
import com.porto.projectmanagement.model.security.UserModel;
import com.porto.projectmanagement.service.security.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class auth {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers(@RequestBody UserModel user){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserModel> getUserById(@RequestBody UserModel user,@PathVariable("username") String username){
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PostMapping("/saveUser")
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user){
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/saveUser").toUriString());
            return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/saveRole")
    public ResponseEntity<RoleEntity> saveUser(@RequestBody RoleEntity role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/saveRole").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/user/addRoleToUser")
    public ResponseEntity<RoleEntity> saveUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUserName(), form.getName());
        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToUserForm{
    private String userName;
    private String name;
}
