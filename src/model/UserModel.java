package model;

public class UserModel {
    private String userId;
    private String name;
    private String password;
    private String logtime;
    private boolean status;

    public UserModel(String userId, String name, String password, String logtime, boolean status) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.logtime = logtime;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
