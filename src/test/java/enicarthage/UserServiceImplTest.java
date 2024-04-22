package com.example.demo;

import Exceptions.UserAlreadyExistException;
import Exceptions.UserNotFoundException;
import Entities.User;
import Repositories.UserRepository;
import Services.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest extends ProjetSpringApplicationTests {
	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        assertThrows(UserNotFoundException.class, () -> userService.getall());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testAddUser() throws UserAlreadyExistException {
        User user = new User("username");
        when(userRepository.findById("username")).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.addUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById("username");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAddUser_UserAlreadyExist() {
        User user = new User("username");
        when(userRepository.findById("username")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistException.class, () -> userService.addUser(user));

        verify(userRepository, times(1)).findById("username");
        verify(userRepository, never()).save(user);
    }

    @Test
    public void testGetUserByUserName() throws UserNotFoundException {
        User user = new User("username");
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById("username")).thenReturn(optionalUser);

        User result = userService.getUserByUserName("username");

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById("username");
    }

    @Test
    public void testGetUserByUserName_UserNotFound() {
        when(userRepository.findById("username")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserByUserName("username"));

        verify(userRepository, times(1)).findById("username");
    }

}
