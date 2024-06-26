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

import java.time.LocalDate;
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
    static final int DESCRIPTION_LENGTH = 200;
    static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    private final Map<Long, Film> films = new HashMap<>();

    @GetMapping
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        checkBody(film);

        film.setId(FilmorateApplication.getNextId(films));
        films.put(film.getId(), film);
        log.info("POS request: {}", film);
        return film;

    }

    @PutMapping
    public Film update(@Valid @RequestBody Film newFilm) {
        if (newFilm.getId() == null) {
            throw new IllegalArgumentException("Id должен быть указан");
        }

        if (films.containsKey(newFilm.getId())) {
            checkBody(newFilm);

            Film oldFilm = films.get(newFilm.getId());
            oldFilm.setName(newFilm.getName());
            oldFilm.setDescription(newFilm.getDescription());
            oldFilm.setDuration(newFilm.getDuration());
            oldFilm.setReleaseDate(newFilm.getReleaseDate());
            log.info("PUT request: {}", newFilm);
            return oldFilm;
        }
        throw new NotFoundException("Фильм с id = " + newFilm.getId() + " не найден");
    }

    private void checkBody(Film film) {
        if (film.getDescription() != null && film.getDescription().length() > DESCRIPTION_LENGTH) {
            throw new ConditionsNotMetException("Описание не может превышать " + DESCRIPTION_LENGTH + " символов");
        }
        if (film.getReleaseDate().isBefore(MIN_RELEASE_DATE)) {
            throw new ConditionsNotMetException("Дата релиза не может быть раньше " + MIN_RELEASE_DATE);
        }
        if (film.getDuration().toSeconds() <= 0) {
            throw new ConditionsNotMetException("Укажите корректную продолжительность фильма");
        }
    }


}
