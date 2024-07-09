package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

/**
 * This class represents a user in a social network.
 */
@Data
@EqualsAndHashCode(of = {"email"})
public class User {
    private Long id;

    private String name;

    @Email
    private String email;

    @NotBlank
    private String login;

    private LocalDate birthday;

    private Set<Long> friends;
}

