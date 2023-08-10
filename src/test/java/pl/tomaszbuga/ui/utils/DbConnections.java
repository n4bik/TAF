package pl.tomaszbuga.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public abstract class DbConnections {
    static Properties props;
    static String url = "jdbc:postgresql://localhost:5432/postgres";

    private static void setup() {
        props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");
    }

    public static void firstSQL() {
        String sql = "SELECT * FROM exhibits";
        setup();

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                System.out.println(rs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void resultSetTest() {
        setup();
        
        String sql = "SELECT * FROM exhibits";
        
        var map = new HashMap<Integer, List<String>>();

        try (Connection conn = DriverManager.getConnection(url, "postgres", "password");
             var ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String num_acres = rs.getString("num_acres");

                List<String> temporaryList = new ArrayList<>();
                temporaryList.add(name);
                temporaryList.add(num_acres);

                map.put(id, temporaryList);
            }
            System.out.println(map);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
