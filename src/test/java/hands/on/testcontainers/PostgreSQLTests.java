package hands.on.testcontainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
public class PostgreSQLTests {

    private String jdbcUrl;
    private String username;
    private String password;

    @Container
    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @BeforeEach
    public void setUp() {
        jdbcUrl = postgreSQLContainer.getJdbcUrl();
        username = postgreSQLContainer.getUsername();
        password = postgreSQLContainer.getPassword();
    }

    @Test
    public void testBasicJdbc() {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {

            assertTrue(conn.isValid(10));
        } catch (SQLException e) {
            fail(e);
        }
    }

}
