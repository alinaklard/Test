package servlet;

import authentication.HibernateAuth;
import authentication.SQLAuth;
import data.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    public static final String SIGN_IN_PAGE_URL = "/Project15_war_exploded/signin.jsp";
    public static final String MAIN_PAGE_URL = "/Project15_war_exploded/main.jsp";
    public static final String LOGIN_PARAM_NAME = "login";
    public static final String PASSWORD_PARAM_NAME = "pass";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HibernateAuth hibernateAuth =new HibernateAuth();
        SQLAuth sqlAuth =new SQLAuth();

        String login = request.getParameter(LOGIN_PARAM_NAME);
        String password = request.getParameter(PASSWORD_PARAM_NAME);
        User user = hibernateAuth.signIn(login,password);
        if (user == null) {
            response.sendRedirect(SIGN_IN_PAGE_URL);
        } else {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(MAIN_PAGE_URL);
        }
    }
}
