package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * This class represents a user in a social network.
 */
@Data
@EqualsAndHashCode(of = {"email"})
public class User {
    /**
     * Unique identifier of the user.
     */
    private Long id;

    /**
     * Name of the user.
     */
    private String name;

    /**
     * Email of the user. This field is used for equality checks and hashing.
     */
    @Email
    private String email;

    /**
     * Login of the user.
     */
    @NotBlank
    private String login;

    /**
     * Birthday of the user.
     */
    private LocalDate birthday;
}

