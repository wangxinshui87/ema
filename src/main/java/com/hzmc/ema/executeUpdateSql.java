package com.hzmc.ema;

import java.sql.SQLException;
import java.sql.Statement;

public class executeUpdateSql extends executeSql {

	public executeUpdateSql() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execteSql() 
	{
		if (sqltext == null)
		{
			System.out.println("请先调用getSqlText函数,输入要执行的sql语句");
			getSqlText(/*e.g*/"update persons set AGE = 20 where ID = 3");
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
			System.out.println("更新数据失败");
		}

	}

}

