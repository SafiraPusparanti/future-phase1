package connection;

public class ConnectionDAO {
    public String database;
    public String url;
    public String username;
    public String password;

    public ConnectionDAO() {
        this.database = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/safirapusparanti?currentSchema=kanmakan";
        this.username = "postgres";
        this.password = "dbfira";
    }
}
