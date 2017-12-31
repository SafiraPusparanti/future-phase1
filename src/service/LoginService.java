package service;

import model.UserModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService {
    private void openConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/safirapusparanti?currentSchema=kanmakan",
                    "postgres",
                    "dbfira"
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
            System.out.println("Failure on closing db connection : " + e.getMessage());
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
