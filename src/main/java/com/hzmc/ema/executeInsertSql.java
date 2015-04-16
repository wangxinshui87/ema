package com.hzmc.ema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class executeInsertSql extends executeSql {

	public executeInsertSql() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execteSql()
	{
		if (sqltext == null)
		{
			System.out.println("请先调用getSqlText函数,输入要执行的sql语句");
			getSqlText(/*e.g*/"INSERT INTO persons(ID, AGE, NAEM ,ISLIVE VALUES (3, 18, 'wangwu', 1)");	
		}
		
		ConnectTodb();
		try
		{
			Statement st = conn.createStatement();
			int count = st.executeUpdate(sqltext);
			System.out.println("XXX表 " + count + " 条数据\n");
		}
		catch (SQLException e)
		{
			System.out.println("插入数据失败"+ e.getMessage());
		}
	}

}
