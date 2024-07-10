package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {
    User createUser(User user);

    User update(User newUser);

    Collection<User> getAll();

    User addFriend(long useId, long friendId);

    Collection<User> getFriends(long userId);

    User deleteFriend(long id, long friendId);

    Collection<User> getCommonFriends(long userId, long otherId);

    User getUser(long userId);

}


