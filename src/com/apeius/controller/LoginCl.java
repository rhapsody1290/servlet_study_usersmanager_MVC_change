package com.apeius.controller;

import com.apeius.domain.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if("qm".equals(username) && "123".equals(password)){
			//跳转到下一个页面，servlet提供了两种，sendRedirect 转向 和 forward 转发
			//sendRediret的url应该/web应用名/servlet
			request.getSession().setAttribute("username",username);
			response.sendRedirect("/UsersManager/MainFrame");
		}else{
			response.sendRedirect("/UsersManager/Login");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
