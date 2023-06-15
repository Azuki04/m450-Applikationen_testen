package ch.web.web_shop.controller;

import ch.web.web_shop.dto.UserDTO;
import ch.web.web_shop.model.User;
import ch.web.web_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
        return userService.loginUser(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long id) {
        return userService.getUserDetails(id);
    }
}
