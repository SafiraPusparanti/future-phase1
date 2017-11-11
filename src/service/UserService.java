package service;

import model.UserModel;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    UserModel getUser(String userId) throws SQLException;
    List<UserModel> getAllUsers() throws SQLException;
    String getMaxId(boolean role) throws SQLException;
    void addUser(UserModel user) throws SQLException;
    void deleteUser(String userId) throws SQLException;
}
