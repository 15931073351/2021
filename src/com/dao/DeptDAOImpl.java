package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdbc.ConnectionDB;

public class DeptDAOImpl implements DeptDAO {

	public List<Map<String, Object>> getEmpByDid(int did) {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			String sql = "select eid,ename from t_emp where did= " + did;
			ResultSet rs = ConnectionDB.getConnection().createStatement().executeQuery(sql);
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("eid", rs.getInt(1));
				m.put("ename", rs.getString(2));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllDeptSelect() {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			Connection conn = ConnectionDB.getConnection();
			Statement st = conn.createStatement();
			String sql = "Select did,dname from t_dept";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("did", rs.getInt(1));
				m.put("dname", rs.getString(2));
				list.add(m);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Map<String, Object> getEmpById(int eid) {
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			String sql = "select eid,ename,username from t_emp where eid= " + eid;
			ResultSet rs = ConnectionDB.getConnection().createStatement().executeQuery(sql);
			while (rs.next()) {		
				m.put("eid", rs.getInt(1));
				m.put("ename", rs.getString(2));
				m.put("username", rs.getString(3));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
		
	}
	

}
