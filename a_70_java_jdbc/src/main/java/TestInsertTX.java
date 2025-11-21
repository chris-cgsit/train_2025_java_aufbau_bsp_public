import java.sql.*;

public class TestInsertTX {

    static void main() throws SQLException {

        Connection conn = MakeDbConnection.makeMyConnection();

        try {

            TestEntity entity = new TestEntity();
            entity.setAktiv(Boolean.TRUE);
            entity.setName("insert with tx");

            // JDBC startet Transaktionen automatisch, sobald setAutoCommit(false) gesetzt wird.
            conn.setAutoCommit(false);

            TestInsertTX.insertTest(conn, entity);
            TestInsertTX.insertTest(conn, new TestEntity("name tx 2", true));
            TestInsertTX.insertTest(conn, new TestEntity("name tx 3", true));
            System.out.println("inserted: " + entity.toString());

            conn.commit();

        } catch (Exception e) {
            conn.rollback(); // => zurückrollen
        }


    }

    public static void insertTest(Connection conn, TestEntity entity) throws SQLException {

        String sql = "INSERT INTO test_entity (name, aktiv) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getName());
            ps.setBoolean(2, entity.getAktiv());

            int result = ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    // key next returniert das eingefügte objekt
                    entity.setId(keys.getLong(1));
                    entity.setCreatedAt(keys.getTimestamp("created_at").toInstant());
                }
            }
        }
    }
}

