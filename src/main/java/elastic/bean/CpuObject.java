package elastic.bean;

import java.util.List;

public class CpuObject extends ElasticObject
{
	private double Wait_PCT;
	private double Sys_PCT;
	private double User_PCT;
	private double Used_PCT;
	private List<String> hitList;
	public double getWait_PCT()
	{
		return Wait_PCT;
	}
	public void setWait_PCT(double wait_PCT)
	{
		Wait_PCT = wait_PCT;
	}
	public double getSys_PCT()
	{
		return Sys_PCT;
	}
	public void setSys_PCT(double sys_PCT)
	{
		Sys_PCT = sys_PCT;
	}
	public double getUser_PCT()
	{
		return User_PCT;
	}
	public void setUser_PCT(double user_PCT)
	{
		User_PCT = user_PCT;
	}
	public double getUsed_PCT()
	{
		return Used_PCT;
	}
	public void setUsed_PCT(double used_PCT)
	{
		Used_PCT = used_PCT;
	}
	public List<String> getHitList()
	{
		return hitList;
	}
	public void setHitList(List<String> hitList)
	{
		this.hitList = hitList;
	}
	
	
}
