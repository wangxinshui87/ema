package dataoperate;

import java.sql.SQLException;
import java.sql.Statement;

public final class InsertSqlExcutor extends AbstractSqlExcutor 
{
	public Object ExecuteSql()
	{
		int count = 0;
		try
		{
			Statement st = m_conn.createStatement();
			count = st.executeUpdate(m_sqltext);
			System.out.println("想表中插入了"+count+"条数据");
		}
		catch (SQLException e)
		{
			System.out.println("插入数据失败" + e.getMessage());
		}
		return count;
	}
}
