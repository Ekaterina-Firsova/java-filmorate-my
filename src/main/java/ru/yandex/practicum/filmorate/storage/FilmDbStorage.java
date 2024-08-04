package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dal.FilmRepository;
import ru.yandex.practicum.filmorate.dal.MpaRepository;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component("filmDbStorage")
@RequiredArgsConstructor
public class FilmDbStorage implements FilmStorage {
    private final FilmRepository filmRepository;
    private final MpaRepository mpaRepository;

    @Override
    public Film getFilm(long id) {
        return filmRepository.getFilm(id).
                orElseThrow(() -> new NotFoundException("Фильм с id = " + id + " не найден"));
    }

    @Override
    public Film createFilm(Film film) {
        checkFilm(film);
        film = filmRepository.save(film);
        return film;
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

    private void checkFilm(Film film) {
        if (film.getReleaseDate().isBefore(Film.MIN_RELEASE_DATE)) {
            throw new ConditionsNotMetException("Дата релиза не может быть раньше " + Film.MIN_RELEASE_DATE);
        }
        if (film.getDuration().toSeconds() <= 0) {
            throw new ConditionsNotMetException("Укажите корректную продолжительность фильма");
        }

        Optional<Mpa> mpaOptional = mpaRepository.getMpa(film.getMpa());
        if (mpaOptional.isEmpty()) {
            throw new ConditionsNotMetException("Нет такого MPA");
        }
    }

}
