package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/safirapusparanti?currentSchema=kanmakan";
    public static final String username = "postgres";
    public static final String password = "dbfira";
    public static Connection c;

    public ConnectDB() {
        this.c = null;
    }

    public static Connection getConnection(){
        try{
            Class.forName(database);
            c = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return c;
    }

    public void closeConnection(){
        try{
            c.close();
            System.out.println("closed");
        }catch (Exception e){
            System.out.println("Failure on closing db ConnectDB : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
