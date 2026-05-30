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
public class TournamentRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<Tournament> mapper = (rs, n) -> {
        Tournament t = new Tournament();
        t.setId(rs.getLong("id"));
        t.setName(rs.getString("name"));
        t.setGame(rs.getString("game"));
        t.setStatus(rs.getString("status"));
        return t;
    };

    public TournamentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Tournament insert(Tournament t) {
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO tournaments(name,game,status) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getName());
            ps.setString(2, t.getGame());
            ps.setString(3, t.getStatus());
            return ps;
        }, key);
        t.setId(key.getKey().longValue());
        return t;
    }

    public Optional<Tournament> findById(Long id) {
        List<Tournament> list = jdbc.query("SELECT * FROM tournaments WHERE id=?", mapper, id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public List<Tournament> findAll() {
        return jdbc.query("SELECT * FROM tournaments", mapper);
    }
}
