package com.apeius.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFrame extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>主界面</h1>");
		out.println("<p>username:"+request.getSession().getAttribute("username")+"</p>");
		out.println("<p>password:"+request.getSession().getAttribute("password")+"</p>");
		out.println("<p>sex:"+request.getSession().getAttribute("sex")+"</p>");
		String[] hobby = (String[])request.getSession().getAttribute("hobby");
		if(hobby != null){
			out.println("<p>你的爱好：");
			for(String s : hobby){
				out.println(s);
			}
			out.println("</p>");
		}else{
			out.println("<p>你没有选择爱好</p>");
		}
		out.println("<p>城市:"+request.getSession().getAttribute("city")+"</p>");
		out.println("<p>自我介绍:"+request.getSession().getAttribute("intro")+"</p>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
