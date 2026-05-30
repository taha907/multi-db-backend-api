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
public class TeamRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<Team> mapper = (rs, n) -> {
        Team t = new Team();
        t.setId(rs.getLong("id"));
        t.setName(rs.getString("name"));
        t.setGame(rs.getString("game"));
        return t;
    };

    public TeamRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Team insert(Team team) {
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO teams(name,game) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, team.getName());
            ps.setString(2, team.getGame());
            return ps;
        }, key);
        team.setId(key.getKey().longValue());
        return team;
    }

    public Optional<Team> findById(Long id) {
        List<Team> list = jdbc.query("SELECT * FROM teams WHERE id=?", mapper, id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public List<Team> findAll() {
        return jdbc.query("SELECT * FROM teams", mapper);
    }

    public boolean existsById(Long id) {
        Integer c = jdbc.queryForObject("SELECT COUNT(*) FROM teams WHERE id=?", Integer.class, id);
        return c != null && c > 0;
    }
}
