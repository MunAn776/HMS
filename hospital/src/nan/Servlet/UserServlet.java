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
		//��������
		request.setCharacterEncoding("utf-8");
		//��ȡfunc����
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
		//���Ự�д洢���û�ɾ��
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		//�ص���¼ҳ��
		response.sendRedirect("login.jsp");
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//�����û�����
		User user = new User(password, username);
		//����UserService�еķ���
		HttpSession session = request.getSession();
		boolean isSuccess = userService.checkLogin(user, session);
		if(isSuccess) {
			//��½�ɹ�
			response.sendRedirect("index.jsp");
		} else {
			//��½ʧ��
			//�洢ʧ����Ϣ
			request.setAttribute("msg", "�û����������������");
			//����ת������ҳ����ת
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private void checkKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�õ��������֤��
		String newKey = request.getParameter("newKey");
		//��Session�Ự��ȡ���洢����֤��
		HttpSession session = request.getSession();
		String oldKey = (String)session.getAttribute("oldKey");
		//��ȡ��Ӧ�������
		PrintWriter writer = response.getWriter();
		//���Դ�Сд�Ƚ���֤��
		if(newKey.equalsIgnoreCase(oldKey)) {
			writer.print(true);
		} else {
			writer.print(false);
		}
	}

	private void checkUsernameIsRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		checkUsername(request, response);
	}

	//�û�ע��
	private void registUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�õ�ע��ʱ������
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		//����User���󱣴�����
		User user = new User(uname, password, username, email, new Date(0));
		
		boolean isSuccess = userService.registerUser(user);
		if(isSuccess) {
			//ע��ɹ���ת����
			response.sendRedirect("login.jsp");
		}
	}

	//У������
	private void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		boolean isRegist = userService.checkEmail(email);
		response.getWriter().print(isRegist);
	}
	//У���û���
	private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		boolean isRegist = userService.checkUsername(username);
		response.getWriter().print(isRegist);
	}

}
