package cn.apeius.controller;

import cn.apeius.domain.Users;
import cn.apeius.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginCl extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String[] hobby = request.getParameterValues("hobby");
        String city = request.getParameter("city");
        String intro = request.getParameter("intro");

        //创建UsersService对象，完成数据库验证
        UsersService usersService = new UsersService();
        Users user = new Users();
        user.setUsername(username);
        user.setPasswd(password);
        if (usersService.checkUser(user)) {
            //跳转到下一个页面，servlet提供了两种，sendRedirect 转向 和 forward 转发
            //sendRediret的url应该/web应用名/servlet
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("sex", sex);
            request.getSession().setAttribute("hobby", hobby);
            request.getSession().setAttribute("city", city);
            request.getSession().setAttribute("intro", intro);
            request.getRequestDispatcher("/MainFrame").forward(request, response);
        } else {
            //如果用户名/密码错误，则给出错误提示
            request.setAttribute("err","用户名或密码错误");
            request.getRequestDispatcher("/Login").forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}
