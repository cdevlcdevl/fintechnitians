package com.hackathon.finclusion.repos;

import com.hackathon.finclusion.models.Account;
import com.hackathon.finclusion.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

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
                (rs, rowNum) -> new User(
                        rs.getString(USER_NAME_COLUMN),
                        rs.getString(USER_EMAIL_COLUMN)
                )
        );
    }

    private static final String USER_NAME_COLUMN = "name";
    private static final String USER_EMAIL_COLUMN = "email";

}
