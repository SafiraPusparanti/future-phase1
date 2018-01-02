package service;

import connection.ConnectionDAO;
import model.UserModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService {

    public static ConnectionDAO connect = new ConnectionDAO();
    public static final String database = connect.database;
    public static final String url = connect.url;
    public static final String username = connect.username;
    public static final String password = connect.password;

    private void openConnection(){
        try{
            Class.forName(database);
            c = DriverManager.getConnection(
                    url,
                    username,
                    password
            );
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("gagal");
            System.exit(0);
        }
        System.out.println("Open");
    }

    public void closeConnection(){
        try{
            c.close();
            System.out.println("closed");
        }catch (Exception e){
            System.out.println("Failure on closing db ConnectionDAO : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean check;
    private Connection c = null;
    //public boolean role;
    public boolean checkUser (UserModel user){
        openConnection();
        try{
            String sql = "SELECT user_id, password FROM user_pos WHERE user_id = ? AND password = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());

            ResultSet rs = ps.executeQuery();
            //role = rs.getBoolean("role");

            check = rs.next();
            closeConnection();
            ps.close();
        }catch (Exception e){
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
        if(check){
            return true;
        }else return false;
    }
}
