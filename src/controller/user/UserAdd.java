package controller.user;

import model.UserModel;
import service.UserService;
import service.UserServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/users/add")
public class UserAdd extends HttpServlet {
    UserService userService = new UserServiceDatabase();

    //TODO: create password generator
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strRole = request.getParameter("role");
        boolean role = false;
        if(strRole.equals("T")) {
            role = true;
        }

        int idNo = 0;
        try {
            String strIdNo = userService.getMaxId(role);
            strIdNo = strIdNo.substring(3, 6);
            idNo = Integer.parseInt(strIdNo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String userId;
        if(role) {
            userId = "ADM" + String.format("%03d", ++idNo);
        } else {
            userId = "CSH" + String.format("%03d", ++idNo);
        }

        try{
            UserModel user = new UserModel( userId,
                    request.getParameter("name"),//id, name, email, pass, logtime, role
                    request.getParameter("email"),
                    request.getParameter("password").hashCode() + "",
                    role);

            userService.addUser(user);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
