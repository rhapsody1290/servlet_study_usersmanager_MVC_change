package cn.apeius.controller;

import cn.apeius.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Asus on 2016/7/11.
 */
public class DeleteCl extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //接收id
        String id = request.getParameter("id");
        UsersService usersService = new UsersService();
        if(usersService.deleteUser(id)){
            request.setAttribute("url", "/UsersManager/ManageUsers");
            request.getRequestDispatcher("/ok").forward(request,response);
        }else{
            request.getRequestDispatcher("/error").forward(request,response);
        }
    }
}
