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
		//��������
		request.setCharacterEncoding("utf-8");
		//��ȡfunc����
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
		case "checkCardno":							//У�����
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

	//ɾ��ҽ���ķ���
	private void deleteDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//��ȡid�ַ���
		String ids = request.getParameter("ids");
		//����DoctorService����������ɾ���ķ���
		boolean isSuccess = doctorService.deleteDoctor(ids);
		if(isSuccess) {
			//ɾ���ɹ����ص�չʾҳ�棬��ʾ��������
			response.sendRedirect("doctor?func=findAllDoctors&currentPage=1");
		}
	}

	//�޸�ҽ���ķ���
	private void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ȡ����ҽ����Ϣ
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
		//����Doctor����洢����	�������ַ���ת�������ڶ���	������ʽ��ת���Ķ���
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
			//��ӳɹ�����ת��չʾҳ�棬����չʾ����
			response.sendRedirect("doctor?func=findAllDoctors&currentPage=1");
		} else {
			//���ʧ�ܣ��ص�ҽ�������ҳ�棬���½����������
			Doctor d = doctorService.findDoctorByDid(did);
			request.setAttribute("doctor", d);
			request.setAttribute("msg", "�޸�ʧ�ܣ��������޸���Ϣ");
			request.getRequestDispatcher("doctor/edit.jsp").forward(request, response);
		}
	}

	//ͨ������did��ȡҽ���ķ���
	private void findDoctorByDid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҽ��������did
		String did = request.getParameter("did");
		//����doctorService�����еķ�����ȡҽ��
		Doctor doctor = doctorService.findDoctorByDid(did);
		//���õ���ҽ�������������
		request.setAttribute("doctor", doctor);
		//����ת�����޸�ҳ��
		request.getRequestDispatcher("doctor/edit.jsp").forward(request, response);
	}

	//��ȡҽ������
	private void lookDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҽ����did
		String did = request.getParameter("did");
		//����DoctorService�еĻ�ȡ����
		Doctor doctor = doctorService.findDoctorByDid(did);
		//�õ���ҽ����look.jsp��չʾ	�漰���ݴ��ݺ�·����ת����������ת��
		//�洢ҽ������
		request.setAttribute("doctor", doctor);
		request.getRequestDispatcher("doctor/look.jsp").forward(request, response);
	}

	private void insertDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ȡ����ҽ����Ϣ
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
		//����Doctor����洢����	�������ַ���ת�������ڶ���	������ʽ��ת���Ķ���
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
			//��ӳɹ�����ת��չʾҳ�棬����չʾ����
			response.sendRedirect("doctor?func=findAllDoctors&currentPage=1");
		} else {
			//���ʧ�ܣ��ص�ҽ�������ҳ�棬���½����������
			request.setAttribute("msg", "���ʧ�ܣ������������Ϣ");
			request.getRequestDispatcher("doctor/add.jsp").forward(request, response);
		}
	}

	//��ȡ����ҽ���ķ���
	private void findAllDoctors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ҳ���߶���
		//������������ѯ���ݿ�õ�
		int totalCount = doctorService.selectDoctorCount();
		//��ҳ�������л�ȡ��ǰҳ��
		String currentPage = request.getParameter("currentPage");
		PageTool pageTool = new PageTool(totalCount, currentPage);
		//����DoctorService�����л�ȡ����ҽ���ķ���
		List<Doctor> doctors = doctorService.findAllDoctors(pageTool);
		//�洢����
		request.setAttribute("doctors", doctors);
		//����ҳ���߶���洢����
		request.setAttribute("pageTool", pageTool);
		//����ת����չʾҳ��
		request.getRequestDispatcher("doctor/index.jsp").forward(request, response);
	}
}
