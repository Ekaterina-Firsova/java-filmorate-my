package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.FilmorateApplication;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller для обслуживания фильмов
 */
@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    //private final Map<Long, Film> films = new HashMap<>();
    private final InMemoryFilmStorage inMemory = new InMemoryFilmStorage();

    @GetMapping
    public Collection<Film> findAll() {
        return inMemory.findAll();
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.info("POST request: {}", film);
        return inMemory.createFilm(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film newFilm) {
        log.info("PUT request: {}", newFilm);
        return inMemory.update(newFilm);
    }


}
