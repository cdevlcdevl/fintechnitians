package com.hackathon.finclusion.repos;

import com.hackathon.finclusion.models.Account;
import com.hackathon.finclusion.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo {
    private final JdbcTemplate jdbcTemplate;

    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        String sql = "SELECT name, email FROM users";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> getUserFromResults(rs)
        );
    }

    public Optional<User> findByName(String name) {
        String sql = "SELECT name, email FROM users WHERE name = ?";
        try {
            User user = jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> getUserFromResults(rs),
                    name // This argument is passed to the '?' placeholder
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    public List<User> findByEmail(String email) {
        String sql = "SELECT name, email FROM users WHERE email = ?";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> getUserFromResults(rs),
                email
        );
    }

    private User getUserFromResults(ResultSet rs) throws SQLException {
        return new User(
                rs.getString(USER_NAME_COLUMN),
                rs.getString(USER_EMAIL_COLUMN)
        );
    }

    private static final String USER_NAME_COLUMN = "name";
    private static final String USER_EMAIL_COLUMN = "email";

}
