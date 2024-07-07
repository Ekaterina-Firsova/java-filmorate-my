package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {
    public Film createFilm (Film film);
    public Film update (Film film);
    public Collection<Film> findAll();
}
