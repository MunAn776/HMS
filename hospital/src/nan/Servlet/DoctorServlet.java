package nan.Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import nan.Entity.Doctor;
import nan.Service.DoctorService;
import nan.Utils.PageTool;

@WebServlet("/doctor")

public class DoctorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DoctorService doctorService = new DoctorService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理乱码
		request.setCharacterEncoding("utf-8");
		//获取func参数
		String func = request.getParameter("func");
		System.out.println(func);
		switch (func) {
		case "findAllDoctors":
			findAllDoctors(request, response);
			break;
		case "insertDoctor":
			insertDoctor(request, response);
			break;
		case "lookDoctor":
			lookDoctor(request, response);
			break;
		case "findDoctorByDid":
			findDoctorByDid(request, response);
			break;
		case "updateDoctor":
			updateDoctor(request, response);
			break;
		case "deleteDoctor":
			deleteDoctor(request, response);
			break;
		case "checkCardno":							//校验操作
			checkCardno(request, response);
			break;
		case "checkPhone": 
			checkPhone(request, response);
			break;
		case "checkEmail":
			checkEmail(request, response);
			break;
		default:
			break;
		}
	}

	private void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		boolean isRegist = doctorService.checkEmail(email);
		response.getWriter().print(isRegist);
	}

	private void checkPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String phone = request.getParameter("phone");
		boolean isRegist = doctorService.checkPhone(phone);
		response.getWriter().print(isRegist);
	}

	private void checkCardno(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cardno = request.getParameter("cardno");
		boolean isRegist = doctorService.checkCardno(cardno);
		response.getWriter().print(isRegist);
	}

	//删除医生的方法
	private void deleteDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取id字符串
		String ids = request.getParameter("ids");
		//调用DoctorService对象中批量删除的方法
		boolean isSuccess = doctorService.deleteDoctor(ids);
		if(isSuccess) {
			//删除成功，回到展示页面，显示最新数据
			response.sendRedirect("doctor?func=findAllDoctors&currentPage=1");
		}
	}

	//修改医生的方法
	private void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取所有医生信息
		String did = request.getParameter("did");
		String dname = request.getParameter("dname");
		String cardno = request.getParameter("cardno");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String department = request.getParameter("department");
		String degree = request.getParameter("degree");
		String mark = request.getParameter("mark");
		//调用Doctor对象存储数据	将日期字符串转换成日期对象	构建格式化转换的对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Doctor doctor = new Doctor(Integer.valueOf(did), dname, cardno, phone, Integer.valueOf(gender), Integer.valueOf(age), date, email, Integer.valueOf(department), Integer.valueOf(degree), mark);
		boolean isSuccess = doctorService.updateDoctor(doctor);
		if(isSuccess) {
			//添加成功，跳转到展示页面，重新展示数据
			response.sendRedirect("doctor?func=findAllDoctors&currentPage=1");
		} else {
			//添加失败，回到医生的添加页面，重新进行数据添加
			Doctor d = doctorService.findDoctorByDid(did);
			request.setAttribute("doctor", d);
			request.setAttribute("msg", "修改失败，请重新修改信息");
			request.getRequestDispatcher("doctor/edit.jsp").forward(request, response);
		}
	}

	//通过主键did获取医生的方法
	private void findDoctorByDid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取医生的主键did
		String did = request.getParameter("did");
		//调用doctorService对象中的方法获取医生
		Doctor doctor = doctorService.findDoctorByDid(did);
		//将得到的医生存在域对象中
		request.setAttribute("doctor", doctor);
		//请求转发到修改页面
		request.getRequestDispatcher("doctor/edit.jsp").forward(request, response);
	}

	//获取医生详情
	private void lookDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取医生的did
		String did = request.getParameter("did");
		//调用DoctorService中的获取方法
		Doctor doctor = doctorService.findDoctorByDid(did);
		//得到的医生在look.jsp中展示	涉及数据传递和路径跳转，采用请求转发
		//存储医生对象
		request.setAttribute("doctor", doctor);
		request.getRequestDispatcher("doctor/look.jsp").forward(request, response);
	}

	private void insertDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//获取所有医生信息
		String dname = request.getParameter("dname");
		String cardno = request.getParameter("cardno");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String department = request.getParameter("department");
		String degree = request.getParameter("degree");
		String mark = request.getParameter("mark");
		//调用Doctor对象存储数据	将日期字符串转换成日期对象	构建格式化转换的对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Doctor doctor = new Doctor(dname, cardno, phone, Integer.valueOf(gender), Integer.valueOf(age), date, email, Integer.valueOf(department), Integer.valueOf(degree), mark);
		boolean isSuccess = doctorService.insertDoctor(doctor);
		if(isSuccess) {
			//添加成功，跳转到展示页面，重新展示数据
			response.sendRedirect("doctor?func=findAllDoctors&currentPage=1");
		} else {
			//添加失败，回到医生的添加页面，重新进行数据添加
			request.setAttribute("msg", "添加失败，请重新添加信息");
			request.getRequestDispatcher("doctor/add.jsp").forward(request, response);
		}
	}

	//获取所有医生的方法
	private void findAllDoctors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//构建分页工具对象
		//总数据量，查询数据库得到
		int totalCount = doctorService.selectDoctorCount();
		//从页面请求中获取当前页码
		String currentPage = request.getParameter("currentPage");
		PageTool pageTool = new PageTool(totalCount, currentPage);
		//调用DoctorService对象中获取所有医生的方法
		List<Doctor> doctors = doctorService.findAllDoctors(pageTool);
		//存储数据
		request.setAttribute("doctors", doctors);
		//将分页工具对象存储起来
		request.setAttribute("pageTool", pageTool);
		//请求转发到展示页面
		request.getRequestDispatcher("doctor/index.jsp").forward(request, response);
	}
}
