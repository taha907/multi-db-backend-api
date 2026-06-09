package com.gamermatch.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<User> mapper = (rs, n) -> {
        User u = new User();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setGameRank(rs.getString("game_rank"));
        return u;
    };

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User insert(User user) {
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username,email,password,game_rank) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getGameRank());
            return ps;
        }, key);
        user.setId(key.getKey().longValue());
        return user;
    }

    public Optional<User> findById(Long id) {
        List<User> list = jdbc.query("SELECT * FROM users WHERE id=?", mapper, id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public List<User> findAll() {
        return jdbc.query("SELECT * FROM users", mapper);
    }

    public boolean existsByEmail(String email) {
        Integer c = jdbc.queryForObject("SELECT COUNT(*) FROM users WHERE email=?", Integer.class, email);
        return c != null && c > 0;
    }

    public boolean existsById(Long id) {
        Integer c = jdbc.queryForObject("SELECT COUNT(*) FROM users WHERE id=?", Integer.class, id);
        return c != null && c > 0;
    }
}
