import java.sql.*;

public class DBConnection {
    private static Connection connection;

    private static String dbName = "root";
    private static String dbUser = "root";
    private static String dbPass = "r54Sk1Hk";

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                            "?user=" + dbUser + "&password=" + dbPass);
            connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
            connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT DEFAULT 1, " +
                    "PRIMARY KEY(id))");
        }
        return connection;
    }


    public static void writeVoters(StringBuilder sqlRequest) throws SQLException {
        DBConnection.getConnection();
        DBConnection.getConnection().createStatement().execute(sqlRequest.toString());
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, COUNT(*) AS q FROM voter_count GROUP BY name ,birthDate HAVING q > 1;";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("q"));
        }
    }


}
