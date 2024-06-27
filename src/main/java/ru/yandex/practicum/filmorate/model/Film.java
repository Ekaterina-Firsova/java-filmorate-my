package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.Duration;
import java.time.LocalDate;

/**
 * This class represents a Film object with properties such as id, name, description, releaseDate, and duration.
 */
@Data
@EqualsAndHashCode(of = {"name", "releaseDate", "duration"})
public class Film {
    public static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);
    public static final int DESCRIPTION_LENGTH = 200;
    private Long id;

    @NotBlank
    private String name;

    private String description;

    private LocalDate releaseDate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Duration duration;
}
