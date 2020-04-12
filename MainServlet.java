package servlet;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import dao.ProductHibernateDAO;
import data.User;
import service.HtmlService;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.*;

public class MainServlet extends javax.servlet.http.HttpServlet {
    public static final String SIGN_IN_PAGE_URL = "/Project15_war_exploded/signin.jsp";
    public static Set<User> users = new LinkedHashSet<User>();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        ProductDAO productDAO = new ProductHibernateDAO();
        if (request.getSession().getAttribute("user") != null) {
            User u = (User) request.getSession().getAttribute("user");
            users.add(u);
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            ServletOutputStream out = response.getOutputStream();
            out.println(HtmlService.mainPage("Main Page",  productDAO.getAllProducts(), u.getType()));
            out.close();
        } else {
            response.sendRedirect(SIGN_IN_PAGE_URL);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}