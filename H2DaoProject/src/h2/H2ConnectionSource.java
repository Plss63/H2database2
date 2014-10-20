package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionSource implements ConnectionSource {
    // private static final String url =
    // "jdbc:h2:mem:C:/Users/Александр/workspace/H2DaoProject/lib/database";
    private static final String url = "jdbc:h2:mem:test;" + "INIT=CREATE SCHEMA IF NOT EXISTS H2BASE;DB_CLOSE_DELAY=-1";
    private static final String driver = "org.h2.Driver";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, "sa", "");
    }

}
