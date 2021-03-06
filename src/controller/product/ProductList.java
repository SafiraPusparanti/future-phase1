package controller.product;

import com.google.gson.Gson;
import model.ProductModel;
import service.ProductService;
import service.ProductServiceDatabase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/products/list")
public class ProductList extends HttpServlet {
    ProductService productService = new ProductServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try{
            List<ProductModel> products = productService.getProductsByCategory(request.getParameter("categoryId"));

            try {
                response.getWriter().write(new Gson().toJson(products));
            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
