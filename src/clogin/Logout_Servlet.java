package clogin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Logout_Servlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException{

        res.setContentType("text/html; charset=Shift_JIS");

        HttpSession session = req.getSession(true);
        session.invalidate();//セッションの破棄

        //ログイン画面へ遷移
        res.sendRedirect("/Login/login.jsp");
    }
}