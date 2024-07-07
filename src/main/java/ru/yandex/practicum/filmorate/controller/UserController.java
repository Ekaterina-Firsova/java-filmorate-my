package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.FilmorateApplication;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final InMemoryUserStorage inMemory = new InMemoryUserStorage();

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        log.info("POS request: {}", user);
        return inMemory.createUser(user);
    }

    @GetMapping
    public Collection<User> findAll() {
        return inMemory.findAll();
    }

    @PutMapping
    public User update(@Valid @RequestBody User newUser) {
        log.info("PUT request: {}", newUser);
        return inMemory.update(newUser);
    }



}
