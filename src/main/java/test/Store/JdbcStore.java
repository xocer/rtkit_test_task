package test.Store;

import org.apache.commons.dbcp2.BasicDataSource;
import test.model.Color;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcStore implements Store {

    private final BasicDataSource pool = new BasicDataSource();

    private JdbcStore() {
        Properties cfg = new Properties();
        try (final InputStream resourceAsStream =
                     JdbcStore.class.getClassLoader().getResourceAsStream("settings.properties"))
        {
            cfg.load(resourceAsStream);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new JdbcStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public List<Color> getAll() {
        List<Color> list = new ArrayList<>();
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from tsveta")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Color(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3)
                    ));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Color getColorById(int id) {
        Color color = null;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from tsveta where id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    color = new Color(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return color;
    }

    @Override
    public String getValue(String colorId) {
        String result = "";
        try (Connection con = pool.getConnection();
             CallableStatement cs = con.prepareCall("{ ? = call get_info(?) }")) {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, colorId);
            cs.execute();
            result = cs.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(JdbcStore.instOf().getValue("08"));
    }
}
