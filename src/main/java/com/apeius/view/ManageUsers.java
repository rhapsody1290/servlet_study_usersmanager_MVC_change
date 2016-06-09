package com.apeius.view;

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
        out.println("<h1>用户管理</h1>");
        //从数据库中取出数据
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int pageNow = 1;
        int pageSize = 5;
        int pageCount = 0;
        int rowCount = 0;

        String sPageNow = request.getParameter("pageNow");
        if(sPageNow != null){
            pageNow = Integer.parseInt(sPageNow);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_users_manager","root","root");
            ps = connection.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
            pageCount = (rowCount - 1) / pageSize + 1;

            ps = connection.prepareStatement("select * from users limit "+(pageNow-1)*pageSize+","+pageSize);
            rs = ps.executeQuery();
            out.println("<table border = 1 width = 500px>");
            out.println("<tr><th>id</th><th>username</th><th>email</th><th>grade</th><th>passwd</th></tr>");
            while(rs.next()){
                out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
            }
            out.println("</table>");
            //打印
            for(int i = 0; i < pageCount; i++){
                out.println("<a href = '/UsersManager/ManageUsers?pageNow="+(i+1)+"'><"+ (i+1) +"></a>");
            }
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
