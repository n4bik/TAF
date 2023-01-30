package pl.tomaszbuga.ui.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public abstract class DbConnections {
    static String url = "jdbc:postgresql://localhost:5432/postgres";

    private static void setup() {
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");
    }

    public static void firstSQL(Properties props) throws SQLException {
        setup();
        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT name FROM exhibits");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                System.out.println(rs.getString(1));
        }
    }

    public static void resultSetTest() throws SQLException {
        setup();
        String sql = "SELECT id, name FROM exhibits";
        var idToNameMap = new HashMap<Integer, String>();

        try (Connection conn = DriverManager.getConnection(url, "postgres", "password");
             var ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                idToNameMap.put(id, name);
            }
            System.out.println(idToNameMap);
        }
    }
}
