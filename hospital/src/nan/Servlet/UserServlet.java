package nan.Servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLClientInfoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sun.net.httpserver.Authenticator.Success;

import nan.Entity.User;
import nan.Service.UserService;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理乱码
		request.setCharacterEncoding("utf-8");
		//获取func参数
		String func = request.getParameter("func");
		switch (func) {
		case "checkUsername":
			checkUsername(request, response);
			break;
		case "checkEmail":
			checkEmail(request, response);
			break;
		case "registUser":
			registUser(request, response);
			break;
		case "checkUsernameIsRegist":
			checkUsernameIsRegist(request, response);
			break;
		case "checkKey":
			checkKey(request, response);
			break;
		case "checkLogin":
			checkLogin(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		default:
			break;
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//将会话中存储的用户删除
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		//回到登录页面
		response.sendRedirect("login.jsp");
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//构建用户对象
		User user = new User(password, username);
		//调用UserService中的方法
		HttpSession session = request.getSession();
		boolean isSuccess = userService.checkLogin(user, session);
		if(isSuccess) {
			//登陆成功
			response.sendRedirect("index.jsp");
		} else {
			//登陆失败
			//存储失败信息
			request.setAttribute("msg", "用户名或密码输入错误！");
			//请求转发进行页面跳转
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private void checkKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//拿到输入的验证码
		String newKey = request.getParameter("newKey");
		//从Session会话中取出存储的验证码
		HttpSession session = request.getSession();
		String oldKey = (String)session.getAttribute("oldKey");
		//获取响应的输出流
		PrintWriter writer = response.getWriter();
		//忽略大小写比较验证码
		if(newKey.equalsIgnoreCase(oldKey)) {
			writer.print(true);
		} else {
			writer.print(false);
		}
	}

	private void checkUsernameIsRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		checkUsername(request, response);
	}

	//用户注册
	private void registUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//拿到注册时的数据
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		//构建User对象保存数据
		User user = new User(uname, password, username, email, new Date(0));
		
		boolean isSuccess = userService.registerUser(user);
		if(isSuccess) {
			//注册成功跳转界面
			response.sendRedirect("login.jsp");
		}
	}

	//校验邮箱
	private void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		boolean isRegist = userService.checkEmail(email);
		response.getWriter().print(isRegist);
	}
	//校验用户名
	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		boolean isRegist = userService.checkUsername(username);
		response.getWriter().print(isRegist);
	}

}
