package controller.product;

import service.ProductService;
import service.ProductServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/products")
public class ProductMenu extends HttpServlet {
    ProductService productService = new ProductServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/admin-products.jsp";

        //TODO: check whether user have already logged in as admin
        request.getRequestDispatcher(address).forward(request, response);
    }
}
