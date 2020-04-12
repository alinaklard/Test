package servlet;

import dao.ProductDAO;
import dao.ProductHibernateDAO;
import data.Product;
import data.User;
import enums.UserType;
import service.HtmlService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "EditProductServlet")
public class EditProductServlet extends HttpServlet {

    public static final String SIGN_IN_PAGE_URL = "/Project15_war_exploded/signin.jsp";
    public static final String MAIN_PAGE_URL = "/Project15_war_exploded/main.jsp";
    public static final String EDIT_PRODUCT_URL = "/Project15_war_exploded/edit_product.jsp?action=view";
    public static final String USER_PARAM_NAME = "user";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(USER_PARAM_NAME);
        if (user != null) {
            if (user.getType() == UserType.ADMIN.getType()) {
                ProductDAO productDAO = new ProductHibernateDAO();
                String action = request.getParameter("action");
                int productID = Integer.parseInt(request.getParameter("id"));

                if (action != null && action.equals("view")) {
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    ServletOutputStream out = response.getOutputStream();
                    out.println(HtmlService.editPage("Edit page", productID));
                    out.close();
                    return;
                }
                if (action != null && action.equals("delete")) {
                    if (productDAO.deleteProduct(productID))
                        response.sendRedirect(MAIN_PAGE_URL);
                    else
                        response.sendRedirect(EDIT_PRODUCT_URL);
                    return;
                }
                String name = request.getParameter("name");
                String photo = request.getParameter("photo");
                String price = request.getParameter("price");
                String mileage = request.getParameter("mileage");
                String fuel = request.getParameter("fuel");
                String transmission = request.getParameter("transmission");
                String ownerNumber = request.getParameter("owner_number");
                String city = request.getParameter("city");
                String place = request.getParameter("place");
                String date = request.getParameter("date");
                Product newProduct = new Product(productID, name, photo, Integer.parseInt(price),
                        Integer.parseInt(mileage), fuel, transmission, ownerNumber, city, place, date);

                Map<String, String> newValues = new HashMap<>();
                Product product = productDAO.getByID(productID);
                if (!product.getName().equals(newProduct.getName()))
                    newValues.put("name", newProduct.getName());
                if (!product.getPicture().equals(newProduct.getPicture()))
                    newValues.put("picture", newProduct.getPicture());
                if (!(product.getPrice() == newProduct.getPrice()))
                    newValues.put("price", String.valueOf(newProduct.getPrice()));
                if (!(product.getMileage() == newProduct.getMileage()))
                    newValues.put("mileage", String.valueOf(newProduct.getMileage()));
                if (!product.getPetrol().equals(newProduct.getPetrol()))
                    newValues.put("petrol", newProduct.getPetrol());
                if (!product.getTransmission().equals(newProduct.getTransmission()))
                    newValues.put("transmission", newProduct.getTransmission());
                if (!product.getOwnerNumber().equals(newProduct.getOwnerNumber()))
                    newValues.put("owner_number", newProduct.getOwnerNumber());
                if (!product.getCity().equals(newProduct.getCity()))
                    newValues.put("city", newProduct.getCity());
                if (!product.getPlace().equals(newProduct.getPlace()))
                    newValues.put("place", newProduct.getPlace());
                if (!product.getDate().equals(newProduct.getDate()))
                    newValues.put("date", newProduct.getDate());
                if (!newValues.isEmpty()) {
                    if (productDAO.updateProduct(newValues, productID))
                        response.sendRedirect(MAIN_PAGE_URL);
                    else
                        response.sendRedirect(EDIT_PRODUCT_URL);

                } else
                    response.sendRedirect(MAIN_PAGE_URL);
            } else
                response.sendRedirect(MAIN_PAGE_URL);
        } else
            response.sendRedirect(SIGN_IN_PAGE_URL);
    }
}
