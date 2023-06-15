package ch.web.web_shop.service;

import ch.web.web_shop.dto.UserDTO;
import ch.web.web_shop.model.User;
import ch.web.web_shop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        UserDTO userDTO = new UserDTO("John Doe", "john@example.com", true, "password");
        User user = new User("John Doe", "john@example.com", true, "password");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user); // Use any(User.class) instead of the specific instance

        ResponseEntity<String> response = userService.registerUser(userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registration successful", response.getBody());

        verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    void testRegisterUser_EmailExists() {
        UserDTO userDTO = new UserDTO("John Doe", "john@example.com", true, "password");
        User existingUser = new User("John Doe", "john@example.com", true, "password");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(existingUser);

        ResponseEntity<String> response = userService.registerUser(userDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("E-Mail already exists", response.getBody());

        verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLoginUser_Success() {
        UserDTO userDTO = new UserDTO("John Doe", "john@example.com", true, "password");
        User existingUser = new User("John Doe", "john@example.com", true, "password");
        existingUser.setId(1L);

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(existingUser);

        ResponseEntity<String> response = userService.loginUser(userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody());

        verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
    }

    @Test
    void testLoginUser_InvalidCredentials() {
        UserDTO userDTO = new UserDTO("John Doe", "john@example.com", true, "password");
        User existingUser = new User("John Doe", "john@example.com", true, "wrong_password");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(existingUser);

        ResponseEntity<String> response = userService.loginUser(userDTO);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody());

        verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
    }

    @Test
    void testGetUserDetails_UserExists() {
        Long userId = 1L;
        User existingUser = new User(userId, "John Doe", "john@example.com", true, "password");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        ResponseEntity<UserDTO> response = userService.getUserDetails(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingUser.getName(), response.getBody().getName());
        assertEquals(existingUser.getEmail(), response.getBody().getEmail());
        assertEquals(existingUser.isSubscribed(), response.getBody().isSubscribed());
        assertEquals(existingUser.getPassword(), response.getBody().getPassword());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserDetails_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userService.getUserDetails(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(userRepository, times(1)).findById(userId);
    }
}
