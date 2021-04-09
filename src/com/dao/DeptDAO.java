package com.dao;

import java.util.List;
import java.util.Map;

public interface DeptDAO {
	//123
	public List<Map<String,Object>> getAllDeptSelect();
	public List<Map<String,Object>> getEmpByDid(int did);
	public Map<String,Object> getEmpById(int eid);
}
