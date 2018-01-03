package service;

import dao.UserDAO;
import model.UserModel;

import java.sql.SQLException;
import java.util.List;

public class UserServiceDatabase implements UserService {
    UserDAO userDAO = new UserDAO();

    @Override
    public List<UserModel> getAllUsers() throws SQLException {
        List<UserModel> users = null;

        try {
            users = userDAO.getAllUsers();
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public UserModel getUser(String userId) throws SQLException {
        UserModel user = null;

        try {
            user = userDAO.getUser(userId);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public String getMaxId(boolean role) throws SQLException {
        String maxId = null;

        try {
            maxId = userDAO.getMaxId(role);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        return maxId;
    }

    @Override
    public void addUser(UserModel user) throws SQLException {
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String userId) throws SQLException {
        try {
            userDAO.deleteUser(userId);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkUser (UserModel user) throws SQLException {
        boolean check = false;

        try {
            check = userDAO.checkUser(user);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        return check;
    }
}
