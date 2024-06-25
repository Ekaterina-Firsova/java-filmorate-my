package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ConditionsNotMetException;
import ru.yandex.practicum.filmorate.model.Film;
import java.time.LocalDate;
import java.util.Set;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmControllerTest {
    Validator  validator;
    private FilmController filmController;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        filmController = new FilmController();
    }

    @Test
    public void whenNullName_thenOneConstraintViolations() {
        Film film = new Film();
        film.setName(null);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);

        assertEquals(1, violations.size());
    }

    @Test
    public void whenBlankName_thenOneConstraintViolations() {
        Film film = new Film();
        film.setName(" ");
        Set<ConstraintViolation<Film>> violations = validator.validate(film);

        assertEquals(1, violations.size());
    }

    @Test
    void whenDescriptionLongerDESCRIPTION_LENGTH_thenException() {
        Film testFilm = new Film();
        testFilm.setName("Test Name");
        testFilm.setDescription("*".repeat(201));
        testFilm.setReleaseDate(LocalDate.now().minusYears(20));
        testFilm.setDuration(ofMinutes(47));
        assertThrows(ConditionsNotMetException.class, () -> filmController.createFilm(testFilm));
    }

    @Test
    void whenReleaseDateBeforeMIN_RELEASE_DATE_thenException() {
        Film testFilm = new Film();
        testFilm.setName("Test Name");
        testFilm.setDescription("тестовое описание");
        testFilm.setReleaseDate(LocalDate.of(1894, 12, 28));
        testFilm.setDuration(ofMinutes(47));
        assertThrows(ConditionsNotMetException.class, () -> filmController.createFilm(testFilm));
    }

    @Test
    public void whenNegativeDuration_thenException() {
        Film testFilm = new Film();
        testFilm.setName("Test Name");
        testFilm.setDescription("тестовое описание");
        testFilm.setReleaseDate(LocalDate.of(2020, 12, 28));
        testFilm.setDuration(ofMinutes(-4));
        assertThrows(ConditionsNotMetException.class, () -> filmController.createFilm(testFilm));
    }

}