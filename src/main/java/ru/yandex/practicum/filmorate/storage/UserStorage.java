package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {
    public User createUser(User user);

    public User update(User newUser);

    public Collection<User> getAll();

    public User addFriend(long useId, long friendId);

    public Collection<User> getFriends(long userId);

    User deleteFriend(long id, long friendId);

    Collection<User> getCommonFriends(long userId, long otherId);

    User getUser(long userId);

}


