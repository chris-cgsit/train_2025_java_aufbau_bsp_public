import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeDbConnection {

    static Connection makeMyConnection () throws SQLException {
        Connection conn =
                DriverManager.getConnection("jdbc:h2:tcp://localhost/testdb",
                        "sa", "" );

        conn.setAutoCommit(true);

        return conn;
    }

}
