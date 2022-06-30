package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "P@ssword1!";
    private Boolean authorization;
    private String msgAge;
    private String msgLogin;
    private String msgPass;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String ageS = request.getParameter("age");
        msgAge ="";
        msgPass = "";
        msgLogin = "";
        authorization = true;

        if (login == null || login == "" || !LOGIN.equals(login)) {
            authorization = false;
            msgLogin = " Login entered incorrectly! ";
        }

        if (!validationPass(password) || !PASS.equals(password)) {
            authorization = false;
            msgPass = " Incorrect or insecure password! ";
        }

        if (ageS != null && ageS != "") {
            int age = Integer.parseInt(ageS);
            if (age < 18) {
                authorization = false;
                msgAge = " Sorry, You are under 18 years old! ";
            }
        } else {
            authorization = false;
            msgAge = " Enter age! ";
        }

        if (authorization) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", login);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("incorrect", msgLogin + msgPass + msgAge + "Please try again.");
        }

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null)) {
            session.removeAttribute("user_login");
            session.removeAttribute("incorrect");
        }
        response.sendRedirect("index.jsp");
    }

    public static Boolean validationPass(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!_&()@#$%])(?=\\S+$).{10,}$");
    }
}
