package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.List;

@Component("filmDbStorage")
public class FilmDbStorage implements FilmStorage {
    @Override
    public Film createFilm(Film film) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }

    @Override
    public Collection<Film> getAll() {
        return List.of();
    }

    @Override
    public Film addLike(long filmId, long userId) {
        return null;
    }

    @Override
    public Film deleteLike(long filmId, long userId) {
        return null;
    }

    @Override
    public Collection<Film> getRating(Integer count) {
        return List.of();
    }

    @Override
    public Film getFilm(long id) {
        return null;
    }
}
