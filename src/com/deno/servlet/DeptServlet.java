package com.deno.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.DeptDAO;
import com.dao.DeptDAOImpl;


@WebServlet("/DeptServlet")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String act = request.getParameter("act");
		System.out.println("��ǰ�Ķ�����:act=" + act);
		if ("getEmpByDid".equals(act)) {
			getEmpByDid(request, response);
		} else if ("getAllDept".equals(act)) {
			getAllDept(request, response);
		} else if ("getEmpById".equals(act)){
			getEmpById(request, response);
		}
	}
	//ͨ�����ű�Ż�ȡԱ���б�
	protected void getEmpByDid(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int did = Integer.parseInt(request.getParameter("did"));
		DeptDAO dao = new DeptDAOImpl();
		List<Map<String, Object>> list =dao.getEmpByDid(did);
		StringBuffer str = new StringBuffer();
		for (Map<String, Object> m : list) {
			str.append(m.get("eid") + "," + m.get("ename") + "!");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// �����ݴ��ݵ�ǰ̨ȥ
		PrintWriter out = response.getWriter();
		out.print(str.toString());// json�ĸ�ʽ tostring���ػ��������ַ���
		out.flush();// ��� flush����ϴ
		out.close();// �ر�
	}
	//ͨ��Ա����Ż�ȡԱ����Ϣ
	protected void getEmpById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		DeptDAO dao = new DeptDAOImpl();
		Map<String, Object> m =dao.getEmpById(id);
		StringBuffer str = new StringBuffer();
		//{"id":12,"username":"����","ename":"����"}
		  str.append("{'eid':"+m.get("eid")+",'username':'"+m.get("username")+"','ename':'"+m.get("ename")+"'}");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// �����ݴ��ݵ�ǰ̨ȥ
		PrintWriter out = response.getWriter();
		out.print(str.toString());// json�ĸ�ʽ tostring���ػ��������ַ���
		out.flush();// ��� flush����ϴ
		out.close();// �ر�
	}
	//��ȡ������Ϣ
	protected void getAllDept(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//����ʵ�������
		DeptDAO dao = new DeptDAOImpl();
		//��������list�����ʵ����ļ��� 
		List<Map<String, Object>> list = dao.getAllDeptSelect();
		// response.setCharacterEncoding("UTF-8");
		// request.setAttribute("deptList", list);
		// request.getRequestDispatcher("DeptListPage.jsp").include(request,
		// response);
		StringBuffer str = new StringBuffer();
		for (Map<String, Object> map : list) {
			str.append(map.get("did") + "," + map.get("dname") + "!");
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// �����ݴ��ݵ�ǰ̨ȥ
		PrintWriter out = response.getWriter();
		out.print(str.toString());// json�ĸ�ʽ
		out.flush();// ���
		out.close();// �ر�
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
