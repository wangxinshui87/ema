package elastic.bean;

import java.util.List;

public class CpuObject extends ElasticObject
{
	private double waitPct;
	private double sysPct;
	private double userPct;
	private double usedPct;
	private List<String> hitList;
	public double getWaitPct()
	{
		return waitPct;
	}
	public void setWaitPct(double waitPct)
	{
		this.waitPct = waitPct;
	}
	public double getSysPct()
	{
		return sysPct;
	}
	public void setSysPct(double sysPct)
	{
		this.sysPct = sysPct;
	}
	public double getUserPct()
	{
		return userPct;
	}
	public void setUserPct(double userPct)
	{
		this.userPct = userPct;
	}
	public double getUsedPct()
	{
		return usedPct;
	}
	public void setUsedPct(double usedPct)
	{
		this.usedPct = usedPct;
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
