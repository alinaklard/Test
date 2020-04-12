package servlet;

import dao.ProductDAO;
import dao.ProductDAOImpl;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AddProductServlet")
public class AddProductServlet extends HttpServlet {

    public static final String MAIN_PAGE_URL = "/Project15_war_exploded/main.jsp";
    public static final String ADD_PAGE_URL = "/Project15_war_exploded/add_product.jsp?action=view";
    public static final String SIGN_IN_PAGE_URL = "/Project15_war_exploded/signin.jsp";
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
                    out.println(HtmlService.addPage("Add product", productID));
                    out.close();
                    return;
                }
                String name = request.getParameter("name");
                String photo = request.getParameter("photo");
                int price = Integer.parseInt(request.getParameter("price"));
                int mileage = Integer.parseInt(request.getParameter("mileage"));
                String fuel = request.getParameter("fuel");
                String transmission = request.getParameter("transmission");
                String ownerNumber = request.getParameter("owner_number");
                String city = request.getParameter("city");
                String place = request.getParameter("place");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                String date = LocalDateTime.now().format(formatter);

                Product p = new Product(productID, name, photo, price, mileage, fuel, transmission, ownerNumber, city, place, date);
                if (productDAO.addProduct(p))
                    response.sendRedirect(MAIN_PAGE_URL);
                else
                    response.sendRedirect(ADD_PAGE_URL);
            } else
                response.sendRedirect(MAIN_PAGE_URL);
        } else
            response.sendRedirect(SIGN_IN_PAGE_URL);
    }
}