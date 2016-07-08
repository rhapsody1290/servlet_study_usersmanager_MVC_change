package cn.apeius.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
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
        //从数据库中取出数据
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int pageNow = 1;
        int pageSize = 5;
        int pageCount = 0;
        int rowCount = 0;

        //pageNow有默认值第一页
        String sPageNow = request.getParameter("pageNow");
        if(sPageNow != null){
            pageNow = Integer.parseInt(sPageNow);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_users_manager","root","root");
            ps = connection.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            //把游标下移，重要！！！
            rs.next();
            rowCount = rs.getInt(1);
            pageCount = (rowCount - 1) / pageSize + 1;

            ps = connection.prepareStatement("select * from users limit "+(pageNow-1)*pageSize+","+pageSize);
            rs = ps.executeQuery();
            out.println("<table border = '1px' width = 500px cellspacing = '0'>");
            out.println("<tr><th>id</th><th>username</th><th>email</th><th>grade</th><th>passwd</th></tr>");
            while(rs.next()){
                out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
            }
            out.println("</table>");
            //上一页
            if(pageNow > 1){
                out.println("<a href = '/UsersManager/ManageUsers?pageNow="+ (pageNow -1) +"'>上一页</a>");
            }
            //打印选择第几行
            for(int i = 0; i < pageCount; i++){
                out.println("<a href = '/UsersManager/ManageUsers?pageNow="+(i+1)+"'><"+ (i+1) +"></a>");
            }
            //下一页
            if(pageNow < pageCount){
                out.println("<a href = '/UsersManager/ManageUsers?pageNow="+ (pageNow + 1) +"'>下一页</a>");
            }
            out.println("&nbsp;当前页：" + pageNow + "<br/><br/>");
            out.println("跳转到：<input type = 'text' id = 'pageNow'/>页&nbsp;<input type = 'button' value = '跳转' onclick = 'gotoPage();'>");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
