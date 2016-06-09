package com.apeius.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

		//到数据库中验证
		/*
		* 1.加载驱动
		* 2.得到连接
		* 3.创建PrepareStatment
		* 4.执行操作
		* 5.根据结果做处理
		* */
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获得连接
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_users_manager","root","root");
			//3.创建prepareStatement
			ps = connection.prepareStatement("select * from users where username = ? and passwd = ?");
			ps.setObject(1,username);
			ps.setObject(2,password);
			//4.执行操作
			rs = ps.executeQuery();
			//5.根据结果做处理
			if(rs.next()){
				//说明用户合法

				//跳转到下一个页面，servlet提供了两种，sendRedirect 转向 和 forward 转发
				//sendRediret的url应该/web应用名/servlet
				request.getSession().setAttribute("username",username);
				request.getSession().setAttribute("password",password);
				request.getSession().setAttribute("sex",sex);
				request.getSession().setAttribute("hobby",hobby);
				request.getSession().setAttribute("city",city);
				request.getSession().setAttribute("intro",intro);
				request.getRequestDispatcher("/MainFrame").forward(request,response);
			}else{
				request.getRequestDispatcher("/Login").forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//关闭资源
			if(rs != null){   // 关闭记录集
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace() ;
				}
			}
			if(ps != null){   // 关闭声明
				try{
					ps.close() ;
				}catch(Exception e){
					e.printStackTrace() ;
				}
			}
			if(connection != null){  // 关闭连接对象
				try{
					connection.close() ;
				}catch(Exception e){
					e.printStackTrace() ;
				}
			}
		}


		/*if(username != null && password != null){
			//跳转到下一个页面，servlet提供了两种，sendRedirect 转向 和 forward 转发
			//sendRediret的url应该/web应用名/servlet
			request.getSession().setAttribute("username",username);
			request.getSession().setAttribute("password",password);
			request.getSession().setAttribute("sex",sex);
			request.getSession().setAttribute("hobby",hobby);
			request.getSession().setAttribute("city",city);
			request.getSession().setAttribute("intro",intro);
			response.sendRedirect("/UsersManager/MainFrame");
		}else{
			response.sendRedirect("/UsersManager/Login");
		}*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
