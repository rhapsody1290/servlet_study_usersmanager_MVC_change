package com.apeius.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>用户登录</h1>");
		out.println("<form action = '/UsersManager/LoginCl' method = 'post'> ");
		out.print("用户名：<input type = 'text' name = 'username'><br/>");
		out.print("密码：<input type = 'text' name = 'password'><br/>");
		out.print("<input type = 'submit' name = 'password' value = '提交'><br/>");
		out.println("</form>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
