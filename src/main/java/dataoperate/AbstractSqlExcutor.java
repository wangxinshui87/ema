package dataoperate;

import java.sql.Connection;

public abstract class AbstractSqlExcutor implements ExcuteSql
{
	protected String m_sqltext;
	protected Connection m_conn; //连接
	protected OracleConnection m_getConn;//产生连接对象
	
	protected void SetSqlText(String sqltext)
	{
		m_sqltext = sqltext;
	}
	protected void ConnectTodb()
	{
		try
		{
			m_getConn = new OracleConnection();
			m_conn = m_getConn.getConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("ConnectToDb" +e.getMessage());
		}
	}
	public abstract Object ExecuteSql(); 
	public Object templateMethod(String sqltext)
	{
		SetSqlText(sqltext);
		ConnectTodb();
		return ExecuteSql();
	}
}
