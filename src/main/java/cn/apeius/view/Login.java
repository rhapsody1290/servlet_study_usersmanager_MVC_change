package cn.apeius.view;

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
		out.println("用户名：<input type = 'text' name = 'username'><br/>");
		out.println("密码：<input type = 'password' name = 'password'><br/>");
		out.println("<input type = 'radio' name = 'sex' value = '男' checked>男<input type = 'radio' name = 'sex' value = '女'>女<br/>");
		out.println("你的爱好：<input type = 'checkbox' name = 'hobby' value = '体育'>体育<input type = 'checkbox' name = 'hobby' value = '音乐'>音乐<br/>");
		out.println("城市：<select name = 'city'><option value = '北京'>北京</option><option value = '上海'>上海</option></select><br/>");
		out.println("自我介绍：<textarea name = 'intro'></textarea><br/>");
		out.println("提交照片：<input type = 'file' name = 'photo'/><br/>");
		out.println("<input type = 'submit' name = 'submit' value = '提交'><br/>");
		out.println("</form>");
		//错误信息从LoginCL传递过来
		String err = (String) request.getAttribute("err");
		if(null != err){
			out.println("<font color='red'>"+ err +"</font>");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
