package com.hzmc.ema;

import java.sql.Connection;

public abstract class executeSql 
{
	protected String sqltext;
	protected Connection conn; //连接
	protected OracleConnection getConn; //产生连接的对象
	
	public void ConnectTodb()
	{
		try 
		{
			getConn = new OracleConnection();
			/*调用一系列的set url user password*/
			conn = getConn.getConnection();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("ConnectToDb" +e.getMessage());
		}
	}
	
	public void  getSqlText(String sqltext)
	{
		this.sqltext = sqltext;
	}
	public abstract void execteSql();
}

