package cn.apeius.view;

import cn.apeius.domain.Users;
import cn.apeius.service.UsersService;
import cn.apeius.utils.SqlHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

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
        //创建UsersService对象
        UsersService usersService = new UsersService();
        //获得页数
        pageCount = usersService.getPageCount(pageSize);
        //根据pageNow和pageSize获取用户
        ArrayList<Users> users = usersService.getUsersByPage(pageNow, pageSize);

        out.println("<table border = '1px' width = 500px cellspacing = '0'>");
        out.println("<tr><th>id</th><th>username</th><th>email</th><th>grade</th><th>passwd</th><th>删除用户</th><th>修改用户</th></tr>");

        for(Users u : users){
            out.println("<tr><td>" + u.getId() + "</td><td>" + u.getUsername() + "</td><td>" + u.getEmail() + "</td><td>"
                    + u.getGrade() + "</td><td>" + u.getPasswd() + "</td>" +
                    "<td><a onclick = 'return confirm(\"确定是否删除？\");' href = '/UsersManager/DeleteCl?id="+u.getId()+"'>删除用户</a></td><td><a href = ''>修改用户</a></td></tr>");
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
        out.println("跳转到：<input type = 'text' id = 'pageNow'/>页&nbsp;<input type = 'button' value = '跳转' onclick = 'gotoPage();'><br/>");

        out.println("<a href = '/UsersManager/MainFrame'>跳转到主页面</a>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
