package com.hzmc.dataoperate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class UpdateSqlExcutor extends AbstractSqlExcutor 
{
	public Object ExecuteSql()
	{
		int count = 0;
		try
		{
			Statement st = m_conn.createStatement();
			count = st.executeUpdate(m_sqltext);
			System.out.println("xxx表"+count + "条数据\n");
		}
		catch (SQLException e)
		{
			System.out.println("update failed" + e.getMessage());
		}
		return count;
	}
}

