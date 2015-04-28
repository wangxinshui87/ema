package dataoperate;

import java.sql.SQLException;
import java.sql.Statement;

public final class DeleteSqlExcutor extends AbstractSqlExcutor 
{
	public Object ExecuteSql()
	{
		int count = 0;
		try
		{
			Statement st = m_conn.createStatement();
			count = st.executeUpdate(m_sqltext);
			System.out.println("XXX表 " + count + " 条数据\n");
		}
		catch (SQLException e)
		{
			System.out.println("删除数据失败");
		}
		return count;
	}
}
