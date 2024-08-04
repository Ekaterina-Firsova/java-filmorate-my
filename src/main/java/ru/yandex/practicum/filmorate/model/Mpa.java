package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Mpa {
    private int id;
    private String name;
    private String description;
}
