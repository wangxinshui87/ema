package com.hzmc.ema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class executeSelectSql extends executeSql 
{

	public executeSelectSql()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execteSql() 
	{
		if (sqltext == null)
		{
			System.out.println("请先调用getSqlText函数,输入要执行的sql语句");
			getSqlText(/*e.g*/"select *from persons");	
		}
		// TODO Auto-generated method stub
		ConnectTodb();
		try 
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sqltext);
			while (rs.next())
			{
				System.out.println(rs.getString("ID") + " "
						+ rs.getString("isLive") + " " + rs.getString("age")
						+ " " + rs.getString("name"));
			}
			conn.close();
		} 
		catch (SQLException e)
		{
			System.out.println("select failed" + e.getMessage());
		}

	}

}
