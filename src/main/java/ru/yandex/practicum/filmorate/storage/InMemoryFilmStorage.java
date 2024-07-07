package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.FilmorateApplication;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();

    @Override
    public Film createFilm(Film film) {
        checkFilm(film);
        film.setId(FilmorateApplication.getNextId(films));
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film newFilm) {
        if (newFilm.getId() == null) {
            throw new IllegalArgumentException("Id должен быть указан");
        }

        if (films.containsKey(newFilm.getId())) {
            checkFilm(newFilm);

            Film oldFilm = films.get(newFilm.getId());
            oldFilm.setName(newFilm.getName());
            oldFilm.setDescription(newFilm.getDescription());
            oldFilm.setDuration(newFilm.getDuration());
            oldFilm.setReleaseDate(newFilm.getReleaseDate());
            return oldFilm;
        }
        throw new NotFoundException("Фильм с id = " + newFilm.getId() + " не найден");
    }

    @Override
    public Collection<Film> findAll() {
        return films.values();
    }

    private void checkFilm(Film film) {
        if (film.getDescription() != null && film.getDescription().length() > Film.MAX_DESCRIPTION_LENGTH) {
            throw new ConditionsNotMetException("Описание не может превышать " + Film.MAX_DESCRIPTION_LENGTH + " символов");
        }
        if (film.getReleaseDate().isBefore(Film.MIN_RELEASE_DATE)) {
            throw new ConditionsNotMetException("Дата релиза не может быть раньше " + Film.MIN_RELEASE_DATE);
        }
        if (film.getDuration().toSeconds() <= 0) {
            throw new ConditionsNotMetException("Укажите корректную продолжительность фильма");
        }
    }



}
