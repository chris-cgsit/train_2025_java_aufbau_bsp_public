import java.sql.*;

public class TestReadDb {

    static void main() throws SQLException {

        Connection conn = MakeDbConnection.makeMyConnection();

        TestReadDb myInstance = new TestReadDb();
        TestEntity entity = myInstance.loadTest(conn, 2);

        if (entity != null) {
            System.out.println("result: " + entity.toString());
        } else {
            System.out.println("entty not found");
        }

    }

    public TestEntity loadTest(Connection conn, long id) throws SQLException {

        String sql = "SELECT * FROM test_entity WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                TestEntity e = new TestEntity(
                        rs.getString("name"),
                        rs.getBoolean("aktiv")
                );

                e.setId(rs.getLong("id"));
                e.setCreatedAt(rs.getTimestamp("created_at").toInstant());

                return e;
            }
        }
    }


}
