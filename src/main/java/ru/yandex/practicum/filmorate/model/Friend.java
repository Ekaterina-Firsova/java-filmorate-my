package ru.yandex.practicum.filmorate.model;

import lombok.Data;

@Data
public class Friend {
    private long id;
    private long id_user;
    private long id_friend;
    private boolean confirmed;
}
