package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;

@Component("userDbStorage")
public class UserDbStorage implements UserStorage {
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User update(User newUser) {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return List.of();
    }

    @Override
    public User addFriend(long useId, long friendId) {
        return null;
    }

    @Override
    public Collection<User> getFriends(long userId) {
        return List.of();
    }

    @Override
    public User deleteFriend(long id, long friendId) {
        return null;
    }

    @Override
    public Collection<User> getCommonFriends(long userId, long otherId) {
        return List.of();
    }

    @Override
    public User getUser(long userId) {
        return null;
    }
}
