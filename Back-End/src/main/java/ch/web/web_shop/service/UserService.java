package ch.web.web_shop.service;

import ch.web.web_shop.dto.UserDTO;
import ch.web.web_shop.model.User;
import ch.web.web_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            return ResponseEntity.badRequest().body("E-Mail already exists");
        }

        User user = convertToUser(userDTO);
        userRepository.save(user);
        return ResponseEntity.ok("Registration successful");
    }

    public ResponseEntity<String> loginUser(UserDTO userDTO) {
        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser == null || !existingUser.getPassword().equals(userDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // ID of the logged-in user as a text
        String userId = String.valueOf(existingUser.getId());

        // Return the ID along with the success message
        return ResponseEntity.ok(userId);
    }

    public ResponseEntity<UserDTO> getUserDetails(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO userDTO = convertToUserDTO(user);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private User convertToUser(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getEmail(), userDTO.isSubscribed(), userDTO.getPassword());
    }

    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail(), user.isSubscribed(), user.getPassword());
    }
}
