package pl.tomaszbuga.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class DbConnections {
    static String url = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");

        resultSetTest();
    }

    public static void firstSQL(Properties props) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT name FROM exhibits");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                System.out.println(rs.getString(1));
        }
    }

    public static void resultSetTest() throws SQLException {
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
