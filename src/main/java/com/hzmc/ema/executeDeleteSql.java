package com.hzmc.ema;

import java.sql.SQLException;
import java.sql.Statement;

public class executeDeleteSql extends executeSql 
{

	public executeDeleteSql() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execteSql() 
	{
		if (sqltext == null)
		{
			System.out.println("请先调用getSqlText函数,输入要执行的sql语句");
			getSqlText(/*e.g*/"delete from persons where id = 1");
			return;
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
			System.out.println("删除数据失败");
		}

	}
}
