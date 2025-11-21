import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {

    static void main() throws SQLException {

        Connection conn = MakeDbConnection.makeMyConnection();

        TestEntity entity = new TestEntity();
        entity.setId(1L);
        entity.setAktiv(Boolean.TRUE);
        entity.setName("test updated asdfsdf ");

        TestUpdate.updateTest(conn,entity);
    }

    public static boolean updateTest(Connection conn, TestEntity entity) throws SQLException {

        String sql = "UPDATE test_entity SET name = ?, aktiv = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setBoolean(2, entity.getAktiv());
            ps.setLong(3, entity.getId());

            return ps.executeUpdate() > 0;
        }
    }

}
