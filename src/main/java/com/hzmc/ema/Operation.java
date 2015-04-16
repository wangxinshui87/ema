package com.hzmc.ema;

public class Operation 
{
	public Operation()
	{
		// TODO Auto-generated constructor stub
	}
	
	public executeSql getExecute(String sqltype)
	{
		if (sqltype == "SELECT")
		{
			return new executeSelectSql();
		}
		else if (sqltype == "UPDATE")
		{
			return new executeUpdateSql();
		}
		else if (sqltype == "INSERT")
		{
			return new executeInsertSql();
		}
		else if (sqltype == "DELETE")
		{
			return new executeDeleteSql();
		}
		else
		{
			System.out.println("输入错误");
			return null;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
