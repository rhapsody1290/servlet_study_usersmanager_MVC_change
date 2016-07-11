package cn.apeius.view;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * Created by Asus on 2016/7/11.
 */
public class error extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("操作失败<br/>");
        out.println("<a href = '"+ request.getAttribute("url") +"'>返回</a>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
