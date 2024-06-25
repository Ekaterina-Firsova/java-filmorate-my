package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;

    @BeforeEach
    public void setUp() {
        userController = new UserController();
    }

    @Test
    void createValidUser() {
        User testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setLogin("testUser");
        testUser.setName("Test User");
        testUser.setBirthday(LocalDate.now().minusYears(20));

        User createdUser = userController.createUser(testUser);

        assertEquals(testUser.getEmail(), createdUser.getEmail());
        assertEquals(testUser.getLogin(), createdUser.getLogin());
        assertEquals(testUser.getName(), createdUser.getName());
        assertEquals(testUser.getBirthday(), createdUser.getBirthday());
    }

    @Test
    void whenFailEmail_thenOneConstraintViolations()  {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        User user = new User();
        user.setEmail("null@.@");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size());
    }

    @Test
    void whenNullLogin_thenOneConstraintViolation(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        User user = new User();
        user.setLogin(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    void whenBlankLogin_thenOneConstraintViolation(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        User user = new User();
        user.setLogin(" ");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    void whenBlankName_thenAssignLogin() {
        User testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setLogin("testLogin");
        testUser.setName("");
        testUser.setBirthday(LocalDate.now().minusYears(20));

        User createdUser = userController.createUser(testUser);

        assertEquals(testUser.getEmail(), createdUser.getEmail());
        assertEquals(testUser.getLogin(), createdUser.getLogin());
        assertEquals(createdUser.getName(), testUser.getLogin());
        assertEquals(testUser.getBirthday(), createdUser.getBirthday());
    }

    @Test
    void whenBirthdayAfterNow_thenException() {
        User testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setLogin("testLogin");
        testUser.setName("Test Name");
        testUser.setBirthday(LocalDate.now().plusYears(20));

        assertThrows(ConditionsNotMetException.class, () -> userController.createUser(testUser));
    }


}