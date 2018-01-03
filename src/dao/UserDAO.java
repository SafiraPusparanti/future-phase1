package dao;

import connection.ConnectDB;
import model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    ConnectDB connDB;

    public UserDAO() {
        this.connDB = new ConnectDB();
    }

    public List<UserModel> getAllUsers() throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UserModel> users = new ArrayList<UserModel>();

        try {
            ps = conn.prepareStatement("SELECT user_id, name, email, role FROM user_pos");
            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new UserModel(rs.getString(1), rs.getString(2), rs.getString(3), null, rs.getBoolean(4)));
            }
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();

            return users;
        }
    }

    public UserModel getUser(String userId) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserModel user = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM user_pos where user_id = ?");
            ps.setString(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();

            return user;
        }
    }

    public String getMaxId(boolean role) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String maxId = null;
        try {
            ps = conn.prepareStatement("SELECT user_id FROM user_pos WHERE role = ? ORDER BY user_id DESC LIMIT 1");
            ps.setBoolean(1, role);
            System.out.println(ps);

            rs = ps.executeQuery();
            rs.next();
            maxId = rs.getString(1);

        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();

        } finally {
            rs.close();
            ps.close();
            conn.close();

            return maxId;
        }
    }

    public void addUser(UserModel user) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("INSERT INTO user_pos (user_id, name, email, password, role) VALUES (?,?,?,?,?)");
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setBoolean(5, user.isRole());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();

        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
    }

    public void deleteUser(String userId) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("DELETE FROM user_pos where user_id = ?");
            ps.setString(1, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();

        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
    }

    public boolean checkUser (UserModel user) throws SQLException{
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean check = false;

        try{
            ps = conn.prepareStatement("SELECT user_id, password FROM user_pos WHERE user_id = ? AND password = ?");
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();

            check = rs.next();
        }catch (SQLException e){
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            conn.close();

            if(check){
                return true;
            } else {
                return  false;
            }
        }
    }
}
