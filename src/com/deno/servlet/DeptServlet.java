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
		System.out.println("当前的动作是:act=" + act);
		if ("getEmpByDid".equals(act)) {
			getEmpByDid(request, response);
		} else if ("getAllDept".equals(act)) {
			getAllDept(request, response);
		} else if ("getEmpById".equals(act)){
			getEmpById(request, response);
		}
	}
	//通过部门编号获取员工列表
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
		// 将数据传递到前台去
		PrintWriter out = response.getWriter();
		out.print(str.toString());// json的格式 tostring返回缓冲区的字符串
		out.flush();// 清空 flush：冲洗
		out.close();// 关闭
	}
	//通过员工编号获取员工信息
	protected void getEmpById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		DeptDAO dao = new DeptDAOImpl();
		Map<String, Object> m =dao.getEmpById(id);
		StringBuffer str = new StringBuffer();
		//{"id":12,"username":"丁丁","ename":"李四"}
		  str.append("{'eid':"+m.get("eid")+",'username':'"+m.get("username")+"','ename':'"+m.get("ename")+"'}");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		// 将数据传递到前台去
		PrintWriter out = response.getWriter();
		out.print(str.toString());// json的格式 tostring返回缓冲区的字符串
		out.flush();// 清空 flush：冲洗
		out.close();// 关闭
	}
	//获取部门信息
	protected void getAllDept(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//创建实体类对象
		DeptDAO dao = new DeptDAOImpl();
		//创建容器list，存放实体类的集合 
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
		// 将数据传递到前台去
		PrintWriter out = response.getWriter();
		out.print(str.toString());// json的格式
		out.flush();// 清空
		out.close();// 关闭
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
