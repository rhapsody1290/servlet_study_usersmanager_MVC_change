package cn.apeius.controller;

import cn.apeius.domain.Users;
import cn.apeius.service.UsersService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * Created by Asus on 2016/7/11.
 */
public class AddCl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        int grade = Integer.parseInt(request.getParameter("grade"));
        String passwd = request.getParameter("passwd");

        Users u = new Users();
        u.setUsername(username);
        u.setEmail(email);
        u.setGrade(grade);
        u.setPasswd(passwd);

        UsersService usersService = new UsersService();
        if(usersService.addUser(u)){
            request.setAttribute("url","/UsersManager/ManageUsers");
            request.getRequestDispatcher("/ok").forward(request,response);
        }else{
            request.setAttribute("url","/UsersManager/ManageUsers");
            request.getRequestDispatcher("/error").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
