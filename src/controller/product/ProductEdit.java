package controller.product;

import service.ProductService;
import service.ProductServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/products/set-image")
public class ProductEdit extends HttpServlet {
    ProductService productService = new ProductServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String productId = request.getParameter("productId");
            String imageUrl = request.getParameter("imageUrl");
            System.out.println(productId +"  "+ imageUrl);
            productService.setImage(productId, imageUrl);
            response.setStatus(200);

//            request.getRequestDispatcher(address).forward(request,response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
