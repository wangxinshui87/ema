package com.hzmc.dataoperate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class SelectSqlExcutor extends AbstractSqlExcutor 
{
	public Object ExecuteSql()
	{
		try
		{
			Statement st = m_conn.createStatement();
			ResultSet rs = st.executeQuery(m_sqltext);
			return rs;
		}
		catch (SQLException e)
		{
			System.out.println("select failed" + e.getMessage());
		}
		return null;
	}
}
