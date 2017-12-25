package controller.product;

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

import static java.lang.Boolean.TRUE;

@WebServlet("/admin/products/add")
public class ProductAdd extends HttpServlet {
    ProductService productService = new ProductServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String category = request.getParameter("categoryId");
        System.out.println(category);
        int idNo = 0;

        try {
            String strIdNo = productService.getMaxId(category);
            strIdNo = strIdNo.substring(2, 5);
            idNo = Integer.parseInt(strIdNo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String productId = category + String.format("%03d", ++idNo);
        int price = Integer.parseInt(request.getParameter("price"));
        try{
            ProductModel product = new ProductModel( productId,
                    request.getParameter("name"),
                    price,
                    TRUE,
                    request.getParameter("categoryId"),
                    request.getParameter("imageUrl"));
            productService.addProduct(product);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
