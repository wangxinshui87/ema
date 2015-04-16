package com.hzmc.ema;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
public class App 
{
    public static void main( String[] args )
    {
    	Connection con;
    	OracleConnection test = new OracleConnection();
    	con = test.getConnection();
    	if (con == null)
    	{
    		System.out.println("get connection failed");
    	}
      //  System.out.println( "Hello World!" );
    }
}
