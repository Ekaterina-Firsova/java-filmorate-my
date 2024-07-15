package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {
    Film createFilm(Film film);

    Film update(Film film);

    Collection<Film> getAll();

    Film addLike(long filmId, long userId);

    Film deleteLike(long filmId, long userId);

    /**
     * возвращает список из первых count фильмов по количеству лайков.
     * Если значение параметра count не задано, вернет первые 10
     *
     * @param count - количество фильмов, которое надо вернуть
     * @return - коллекция фильмов
     */
    Collection<Film> getRating(Integer count);

    Film getFilm(long id);

}
