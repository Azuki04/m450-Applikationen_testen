package ch.web.web_shop.controller;

import ch.web.web_shop.dto.UserDTO;
import ch.web.web_shop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterUser() {
        UserDTO userDTO = new UserDTO();
        // Set userDTO properties as needed

        ResponseEntity<String> expectedResponse = new ResponseEntity<>("User registered", HttpStatus.OK);

        when(userService.registerUser(userDTO)).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = userController.registerUser(userDTO);

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).registerUser(userDTO);
    }

    @Test
    void testLoginUser() {
        UserDTO userDTO = new UserDTO();
        // Set userDTO properties as needed

        ResponseEntity<String> expectedResponse = new ResponseEntity<>("User logged in", HttpStatus.OK);

        when(userService.loginUser(userDTO)).thenReturn(expectedResponse);

        ResponseEntity<String> actualResponse = userController.loginUser(userDTO);

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).loginUser(userDTO);
    }

    @Test
    void testGetUserDetails() {
        Long userId = 1L;
        UserDTO expectedUserDTO = new UserDTO();
        // Set expectedUserDTO properties as needed

        ResponseEntity<UserDTO> expectedResponse = new ResponseEntity<>(expectedUserDTO, HttpStatus.OK);

        when(userService.getUserDetails(userId)).thenReturn(expectedResponse);

        ResponseEntity<UserDTO> actualResponse = userController.getUserDetails(userId);

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).getUserDetails(userId);
    }
}
