package com.gamermatch.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TeamMemberRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<TeamMember> mapper = (rs, n) -> {
        TeamMember m = new TeamMember();
        m.setId(rs.getLong("id"));
        m.setUserId(rs.getLong("user_id"));
        m.setTeamId(rs.getLong("team_id"));
        return m;
    };

    public TeamMemberRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public TeamMember insert(TeamMember member) {
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO team_members(user_id,team_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, member.getUserId());
            ps.setLong(2, member.getTeamId());
            return ps;
        }, key);
        member.setId(key.getKey().longValue());
        return member;
    }

    public List<TeamMember> findByTeamId(Long teamId) {
        return jdbc.query("SELECT * FROM team_members WHERE team_id=?", mapper, teamId);
    }
}
