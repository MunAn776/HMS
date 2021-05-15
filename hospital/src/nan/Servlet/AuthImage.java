package nan.Servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nan.Utils.*;
/*
 * ������֤��
 */
@WebServlet("/authImage")
public class AuthImage extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("���յ�������~");
		//��֪�������Ҫ������֤��
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg");//��Ӧ����������
           
        //��������ִ� 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        /*
         	��ʱ��Servlet�����ɵ���֤��ΪʲôҪ�洢���ỰSession�����������أ�
         	1��������������֤�룬��ʾ��ҳ��󣬽�����������Ҫ�ֶ�������ͼ���е����ݣ�Ȼ����бȽϣ��Ƿ�������ȷ
         		���Կ϶���Ҫ�����ɵ���֤����д洢
         	2����̨��������ܹ����ĸ���
         		pageContext������jspҳ�棬����֤������Servlet�����ɵģ��ʲ��У�
         		request������һ��������ô������֤��������������֤��������֮�Ͷ�ʧ�ˣ��ʲ��У�
         		��֤��Ĵ洢ʱ�䣬����Ҫ��������Ŀ�����ڼ䣬���Բ���Ҫ�õ�ȫ�ֹ����ߣ�
         	3�����Ự�����󣬿϶���Ҫ���µ�¼����������ѡȡSession��Ϊ��֤��洢�������
         */
        //����Ựsession 
        HttpSession session = request.getSession(); 
        //ɾ����ǰ��
        session.removeAttribute("oldKey");
        //�洢�Ự��Ŀ����Ϊ���������бȽ�
        session.setAttribute("oldKey", verifyCode); 
        //System.out.println(session.getAttribute("verCode"));
        //����ͼƬ 
        int w = 100, h = 30; 
        /*
         	w ����ӦͼƬ�Ŀ��
         	h ����ӦͼƬ�ĸ߶�
         	response.getOutputStream() ��ӦͼƬ��������
         	verifyCode ��Ƕ��ͼƬ�е���֤����Ϣ�����������ɵ���֤���ַ�����
         */
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
	}
}











