package service;

import dao.UserDAO;
import model.UserModel;

import java.sql.SQLException;
import java.util.List;

public class UserServiceDatabase implements UserService {
    UserDAO userDAO = new UserDAO();

    @Override
    public List<UserModel> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    @Override
    public UserModel getUser(String userId) throws SQLException {
        return userDAO.getUser(userId);
    }

    @Override
    public String getMaxId(boolean role) throws SQLException {
        return userDAO.getMaxId(role);
    }

    @Override
    public void addUser(UserModel user) throws SQLException {
        userDAO.addUser(user);
    }

    @Override
    public void deleteUser(String userId) throws SQLException {
        userDAO.deleteUser(userId);
    }
}
