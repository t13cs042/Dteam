package clogin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@SuppressWarnings("serial")
public class Logout_Servlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException{

        res.setContentType("text/html; charset=Shift_JIS");

        HttpSession session = req.getSession(true);
        session.invalidate();

        res.sendRedirect("/Login/login.jsp");
    }
}