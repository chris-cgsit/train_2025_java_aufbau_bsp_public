import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TestReadAllColumns {

    static void main() throws SQLException {
        Connection conn = MakeDbConnection.makeMyConnection();

        //List<TestEntity> testEntities = loadByNamePrefix(conn, null);

        List<TestEntity> testEntities = loadByNamePrefix(conn, "in");

        testEntities.stream().sorted().forEach(System.out::println);
    }

    public static List<TestEntity> loadByNamePrefix(Connection conn, String prefix) throws SQLException {

        String baseSql = "SELECT * FROM test_entity ";
        boolean usePrefix = (prefix != null);

        String sql = usePrefix ? baseSql + "WHERE name LIKE ?" : baseSql;

        List<TestEntity> result = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            if (usePrefix) {
                ps.setString(1, prefix + "%");
            }

            // Ein ResultSet ist ein Cursor über die Ergebnismenge.
            try (ResultSet rs = ps.executeQuery()) {

                // fleissaufgabe das auslesen der vorhanden columnns
                // Wenn die Query z.b. aktiv nicht auswählt, erscheint die Spalte nicht im ResultSet
                // und ist somit weder lesbar noch im MetaData vorhanden.
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                System.out.println("Available columns:");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(" - " + meta.getColumnName(i));
                }

                while (rs.next()) {

                    // wenn das ResultSet scrollable ist → getRow() gültig
                    int rowNum = rs.getRow(); // <-- Positionsnummer vom JDBC-Driver
                    System.out.println("Processing row #" + rowNum);

                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    boolean aktiv = rs.getBoolean("aktiv");
                    var ts = rs.getTimestamp("created_at");

                    // wir mappen die result set daten gleich in ein JAVA objekt.
                    // aber per hand. ein objekt relationales mapping wird von JDBC so nicht unterstützt
                    TestEntity resultEntity = new TestEntity(name, aktiv);
                    resultEntity.setId(id);
                    if (ts != null) {
                        // der java.sql.Timestamp kann ein java instance erzeugen.
                        // da die Timestamp klasse sql spezifisch ist
                        Instant instant = ts.toInstant();
                        resultEntity.setCreatedAt(instant);
                    }

                    // egene objekt result liste
                    result.add(resultEntity);
                }
            }
        }

        return result;
    }

}
