package controller.product;

import service.ProductService;
import service.ProductServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/products/delete")
public class ProductDelete extends HttpServlet {
    ProductService productService = new ProductServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            productService.deleteProduct(request.getParameter("deleteId"));
            response.setStatus(200);

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
