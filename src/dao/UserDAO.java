package dao;

import model.UserModel;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/safirapusparanti?currentSchema=kanmakan";
    public static final String username = "postgres";
    public static final String password = "dbfira";

    Connection conn;

    public UserDAO() {

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public List<UserModel> getAllUsers() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT user_id, name, email, role FROM user_pos");
        ResultSet rs = ps.executeQuery();

        List<UserModel> users = new ArrayList<UserModel>();
        while (rs.next()) {
            users.add(new UserModel(rs.getString(1), rs.getString(2), rs.getString(3), null, rs.getBoolean(4)));
        }
        return users;
    }

    public UserModel getUser(String userId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_pos where user_id = ?");
        ps.setString(1, userId);

        ResultSet rs = ps.executeQuery();

        UserModel output;
        if (rs.next()) {
            output = new UserModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
        } else {
            output = null;
        }
        return output;
    }

    public String getMaxId(boolean role) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT user_id FROM user_pos WHERE role = ? ORDER BY user_id DESC LIMIT 1");
        ps.setBoolean(1, role);
        System.out.println(ps);

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public void addUser(UserModel user) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO user_pos (user_id, name, email, password, role) VALUES (?,?,?,?,?)");
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setBoolean(5, user.isRole());
        System.out.println(ps);
        ps.executeUpdate();
    }

    public void deleteUser(String userId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM user_pos where user_id = ?");
        ps.setString(1, userId);
        ps.executeUpdate();
    }
}
