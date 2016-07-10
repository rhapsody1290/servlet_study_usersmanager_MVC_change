package cn.apeius.view;

import cn.apeius.utils.SqlHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Asus on 2016/6/9.
 */
public class ManageUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        //回送Script
        out.println("<script>function gotoPage(){var pageNow = document.getElementById('pageNow').value;window.location.href = '/UsersManager/ManageUsers?pageNow='+ pageNow;}</script>");
        out.println("<h1>用户管理</h1>");

        int pageNow = 1;
        int pageSize = 5;
        int pageCount = 0;
        int rowCount = 0;

        //pageNow有默认值第一页
        String sPageNow = request.getParameter("pageNow");
        if (sPageNow != null) {
            pageNow = Integer.parseInt(sPageNow);
        }
        ResultSet rs = null;
        try {
            rs = SqlHelper.executeQuery("select count(*) from users", null);
            //把游标下移，重要！！！
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        pageCount = (rowCount - 1) / pageSize + 1;

        rs = SqlHelper.executeQuery("select * from users limit " + (pageNow - 1) * pageSize + "," + pageSize, null);
        out.println("<table border = '1px' width = 500px cellspacing = '0'>");
        out.println("<tr><th>id</th><th>username</th><th>email</th><th>grade</th><th>passwd</th></tr>");
        try {
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getInt(4) + "</td><td>" + rs.getString(5) + "</td></tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println("</table>");
        //上一页
        if (pageNow > 1) {
            out.println("<a href = '/UsersManager/ManageUsers?pageNow=" + (pageNow - 1) + "'>上一页</a>");
        }
        //打印选择第几行
        for (int i = 0; i < pageCount; i++) {
            out.println("<a href = '/UsersManager/ManageUsers?pageNow=" + (i + 1) + "'><" + (i + 1) + "></a>");
        }
        //下一页
        if (pageNow < pageCount) {
            out.println("<a href = '/UsersManager/ManageUsers?pageNow=" + (pageNow + 1) + "'>下一页</a>");
        }
        out.println("&nbsp;当前页：" + pageNow + "<br/><br/>");
        out.println("跳转到：<input type = 'text' id = 'pageNow'/>页&nbsp;<input type = 'button' value = '跳转' onclick = 'gotoPage();'>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
