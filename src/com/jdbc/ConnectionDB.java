package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static String URL = "jdbc:mysql://localhost:3306/rbac";
	private static String USER = "root";
	private static String PWD = "root";

	public static Connection getConnection() {
		//
		Connection conn = null;
		try {
			//加载并注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//通过DriverManager获取数据库连接
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
