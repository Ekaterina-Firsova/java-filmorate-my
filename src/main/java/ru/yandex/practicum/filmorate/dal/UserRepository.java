package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepository extends BaseRepository<User> {
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE users SET name = ?, email = ?, birthday = ?, login = ? WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO users(name, email, birthday, login) " +
            "VALUES (?, ?, ?, ?)";

    // Инициализируем репозиторий
    public UserRepository(JdbcTemplate jdbc, RowMapper<User> mapper) {
        super(jdbc, mapper);
    }

    public List<User> findAll() {
        List<User> users = findMany(FIND_ALL_USERS_QUERY);
        for (User user : users) {
            Set<Long> friendsIds = getALLFriendsIds(user.getId());
            user.setFriends(friendsIds);
        }
        return users;
    }

    private Set<Long> getALLFriendsIds(long id) {
        return new HashSet<>(findManyIds("SELECT id_friend FROM friends WHERE id_user = ?", id));
    }

    public void updateFriendsStatus(Long userId, Long friendId, boolean status) {
        update("UPDATE friends SET confirmed = ? WHERE id_user = ? AND id_friend = ?", status, userId, friendId);
    }

    public Optional<User> findByEmail(String email) {
        return findOne(FIND_BY_EMAIL_QUERY, email);
    }

    public Optional<User> findById(long userId) {
        return findOne(FIND_BY_ID_QUERY, userId);
    }

    public User update(User user) {
        update(
                UPDATE_QUERY,
                user.getName(),
                user.getEmail(),
                user.getBirthday(),
                user.getLogin(),
                user.getId()
        );
        return user;
    }

    public User save(User user) {
        int id = insert(
                INSERT_QUERY,
                user.getName(),
                user.getEmail(),
                user.getBirthday(),
                user.getLogin()
        );
        user.setId((long) id);
        return user;
    }

    public User addFriend(User user, long friendId, boolean status) {
        insert(
                "INSERT INTO friends(ID_USER, ID_FRIEND, CONFIRMED) VALUES (?,?,?)",
                user.getId(),
                friendId,
                status
        );
        if (user.getFriends() == null) {
            user.setFriends(new HashSet<>());
        }
        user.getFriends().add(friendId);
        return user;
    }

    public Collection<User> getFriends(long userId) {
        return findMany(
                "SELECT USERS.*" +
                " FROM PUBLIC.FRIENDS" +
                " LEFT JOIN PUBLIC.users ON FRIENDS.ID_FRIEND = users.ID" +
                " WHERE FRIENDS.id_user = ?", userId);
    }
}

