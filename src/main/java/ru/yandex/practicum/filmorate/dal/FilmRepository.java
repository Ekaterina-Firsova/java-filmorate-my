package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.dto.NewFilmRequest;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Optional;
import java.util.Set;

@Repository
public class FilmRepository extends BaseRepository<Film> {
    public FilmRepository(JdbcTemplate jdbc, RowMapper<Film> mapper) {
        super(jdbc, mapper);
    }

//        Set<Genre> genres = findMany(
//        "SELECT GENRES.*" +
//        "FROM PUBLIC.FILM_GENRES" +
//        "LEFT JOIN PUBLIC.GENRES ON FILM_GENRES.ID_GENRE = GENRES.ID" +
//        "WHERE FILM_GENRES.ID_FILM = ?", film.getId());


    public Film save(Film film) {
        long id = insert(
                "INSERT INTO films(NAME, DESCRIPTION, RELEASE_DATE, DURATION, ID_RATING) " +
                        "VALUES (?, ?, ?, ?, ?)",
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                film.getMpa()
        );

        if (film.getGenres() != null) {
            film.getGenres().forEach(genre -> insert(
                    "INSERT INTO film_genres (id_genre, id_film) " +
                            "VALUES (?, ?)", genre, id));
        }
        film.setId(id);
        return film;
    }

    public Optional<Film> getFilm(long id) {
        return findOne("SELECT * FROM films WHERE ID =?", id);
    }

    public Set<Genre> getFilmGenres(int filmId) {
        return null;
    }

}

