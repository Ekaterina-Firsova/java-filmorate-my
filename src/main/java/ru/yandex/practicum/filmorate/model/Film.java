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
    /**
     * Unique identifier for the Film.
     */
    private Long id;

    /**
     * Name of the Film.
     */
    @NotBlank
    private String name;

    /**
     * Description of the Film.
     */
    private String description;

    /**
     * Release date of the Film.
     */
    private LocalDate releaseDate;

    /**
     * Duration of the Film.
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Duration duration;
}
