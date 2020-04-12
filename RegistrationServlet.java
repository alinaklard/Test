package servlet;

import dao.UserHibernateDAO;
import dao.UserSQLDAO;
import data.User;
import enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    public static final String LOGIN_PARAM_NAME = "login";
    public static final String PASSWORD_PARAM_NAME1 = "pass1";
    public static final String PASSWORD_PARAM_NAME2 = "pass2";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserHibernateDAO hibernateDAO = new UserHibernateDAO();
        UserSQLDAO userSQLDAO = new UserSQLDAO();

        String password1 = request.getParameter(PASSWORD_PARAM_NAME1);
        String password2 = request.getParameter(PASSWORD_PARAM_NAME2);

        if (password1.equals(password2)) {
            User u = new User(request.getParameter(LOGIN_PARAM_NAME), request.getParameter(PASSWORD_PARAM_NAME1), UserType.USER.getType());
            if (hibernateDAO.addUser(u)) {
                response.sendRedirect(MainServlet.SIGN_IN_PAGE_URL);
            } else {
                response.sendRedirect("/Project15_war_exploded/loginError.jsp");
            }
        } else {
            response.sendRedirect("/Project15_war_exploded/passwordConfirm.jsp");
        }
    }

}