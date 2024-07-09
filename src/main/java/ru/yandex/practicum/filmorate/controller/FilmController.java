package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

/**
 * Controller для обслуживания фильмов
 */
@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    public Collection<Film> getAll() {
        return filmService.getAll();
    }

    @GetMapping("{id}")
    public Film getFilm(@PathVariable long id) {
        return filmService.getFilm(id);
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.info("POST createFilm: {}", film);
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film newFilm) {
        log.info("PUT update film: {}", newFilm);
        return filmService.update(newFilm);
    }

    //запрос добавляет лайк определенного пользователя
    @PutMapping("{id}/like/{userId}")
    public Film addLike(@PathVariable long id, @PathVariable long userId) {
        log.info("PUT addLike: {}", id);
        return filmService.addLike(id, userId);
    }

    //запрос удаляет лайк определенного пользователя
    @DeleteMapping("{id}/like/{userId}")
    public Film deleteLike(@PathVariable long id, @PathVariable long userId) {
        log.info("DELETE deleteLike: {}", id);
        return filmService.deleteLike(id, userId);
    }

    //GET /films/popular?count={count}
    //возвращает список из первых count фильмов по количеству лайков.
    // Если значение параметра count не задано, вернет первые 10
    @GetMapping("popular")
    public Collection<Film> getRating(@RequestParam(required = false, defaultValue = "10") String count) {
        return filmService.getRating(Integer.valueOf(count));
    }

}
