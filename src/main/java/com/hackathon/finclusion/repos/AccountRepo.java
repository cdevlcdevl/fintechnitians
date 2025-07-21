package com.hackathon.finclusion.repos;
import com.hackathon.finclusion.models.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AccountRepo {
    private final JdbcTemplate jdbcTemplate;

    public AccountRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> findAll() {
        String sql = "SELECT number, owner FROM accounts";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Account(
                        rs.getString(ACCOUNT_NUMBER_COLUMN),
                        rs.getString(ACCOUNT_OWNER_COLUMN)
                )
        );
    }

    private static final String ACCOUNT_NUMBER_COLUMN = "number";
    private static final String ACCOUNT_OWNER_COLUMN = "owner";
}
