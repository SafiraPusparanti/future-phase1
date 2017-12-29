package controller.product;

import com.google.gson.Gson;
import model.ProductModel;
import service.ProductService;
import service.ProductServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/products/detail")
public class ProductDetail extends HttpServlet {
    ProductService productService = new ProductServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try{
            List<ProductModel> products = new ArrayList<ProductModel>();
            products.add(productService.getProduct(request.getParameter("productId")));

            String currencyWrapper = "";

            for(int i = 0; i < products.size(); i++) {
                currencyWrapper = String.format("Rp %,.2f", products.get(i).getPrice());
                products.get(i).setCurrencyWrapper(currencyWrapper);
            }

            response.getWriter().write(new Gson().toJson(products));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
