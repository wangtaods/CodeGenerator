package com.tao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlDBUtils {

	private static MysqlDBUtils instanse = new MysqlDBUtils();

	private MysqlDBUtils() {
	}

	public static MysqlDBUtils getInstanse() {
		return instanse;
	}

	public  Connection getConn(String ip, String port, String password, String user, String database) {
		try {//"com.mysql.cj.jdbc.Driver"
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection(
					"jdbc:mysql://" + ip + ":" + port + "/" + database
							+ "?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true&autoReconnect=true&useSSL=false",
					user, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public  List<List<String>> getDataList(String ip, String port, String password, String user, String database,String sql) {

		try (Connection conn = getConn(ip, port, password, user, database);) {
			assert conn != null;
			try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
				List<List<String>> list = new ArrayList<List<String>>();
				int col = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					List<String> li = new ArrayList<String>();
					for (int i = 1; i <= col; i++) {
						li.add(rs.getString(i));
					}
					
					list.add(li);
				}

				return list;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public  Map<String, String> queryTable(String ip, String port, String password, String user, String database,String tableName){
		
		String sql = String.format(queryTable, database,tableName);
		System.out.println(sql);
		List<List<String>> dataList = getDataList(ip, port, password, user, database, sql) ;
		Map<String, String> map = new HashMap<String, String>();
		for (List<String> list : dataList) {
			map.put("tableName", list.get(0));
			map.put("engine", list.get(1));
			map.put("tableComment", list.get(2));
			map.put("createTime", list.get(3));
		}
		
		return map;
		
	}
	public  List<Map<String, String>> queryColumns(String ip, String port, String password, String user, String database,String tableName){
		List<List<String>> dataList = getDataList(ip, port, password, user, database, String.format(queryColumns, database,tableName));
		List<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
		for (List<String> list : dataList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("columnName", list.get(0));
			map.put("dataType", list.get(1));
			map.put("columnComment", list.get(2));
			map.put("columnKey", list.get(3));
			map.put("extra", list.get(4));
			list1.add(map);
		}
		return list1;
		
	}
	
	private String queryTable = " select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = '%s' and table_name = '%s'";
			
	private String queryColumns = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_schema = '%s' and  table_name = '%s' order by ordinal_position";
}
