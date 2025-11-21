import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {

    static void main() throws SQLException {

        Connection conn = MakeDbConnection.makeMyConnection();

        TestDelete.deleteById(conn,8L);
        TestDelete.deleteByName(conn, "neuer Eintrag");
    }

    public static void deleteById(Connection conn, long id) throws SQLException {

        String sql = "DELETE FROM test_entity WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("No record found with ID=" + id);
            }
        }
    }

    public static void deleteByName(Connection conn, String name) throws SQLException {

        String sql = "DELETE FROM test_entity WHERE name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }



}
