import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeDbConnection {

    static Connection makeMyConnection () throws SQLException {

        String url = null;
        String user = null;
        String password = null;
        // environement varable setzten um zu entscheiden welche DB verwendet werden soll
        // könnte auch aus einem config file gelesen werden, und oder auch als config hierarchie wie bei
        // spring oder quarkus
        String dbsystem = System.getenv("dbsystem");
        if("postgres".equalsIgnoreCase(dbsystem)) {
            // parameter für postgres
            url = "jdbc:postgresql://localhost:5432/testdb";
            user = "postgres";
            password = "postgres";
        }
        if("h2".equalsIgnoreCase(dbsystem)) {
            // parameter für H2 db
            url = "jdbc:h2:tcp://localhost/testdb";
            user = "sa";
            password = "";
        }

        if(url == null) {
            throw new RuntimeException("keine DB Konfiguration vorhanden");
        }

        Connection conn =
                DriverManager.getConnection(url, user, password );

        conn.setAutoCommit(true);

        return conn;
    }

}
