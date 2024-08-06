package ru.yandex.practicum.filmorate.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class FilmRowMapper implements RowMapper<Film>{

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        //разбор ответа из БД
        Film film = new Film();
        film.setId(rs.getLong("id"));
        film.setName(rs.getString("name"));
        film.setDuration(Duration.ofSeconds(rs.getInt("duration")));
        film.setDescription(rs.getString("description"));
        film.setReleaseDate(rs.getDate("release_date").toLocalDate());
        film.setMpa(rs.getInt("ID_MPA"));

//        Array likesArray = rs.getArray("like_count");
//        if (likesArray != null) {
//            Long[] likes = (Long[]) likesArray.getArray();
//            film.setUsersLike(new HashSet<>(Arrays.asList(likes)));
//        } else {
//            film.setGenres(new HashSet<>());
//        }


//        Array genresArray = rs.getArray("genres");
//        if (genresArray != null) {
//            Integer[] genres = (Integer[]) genresArray.getArray();
//            film.setGenres(new HashSet<>(Arrays.asList(genres)));
//        } else {
//            film.setGenres(new HashSet<>());
//        }

        return film;
    }
}
