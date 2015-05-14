package elastic.bean;

import java.util.Date;

/**
 * 查询条件类
 * index type为必填字段
 * hostname 标示要查询的主机,可以为null
 * @author pairs
 *
 */
public class ElasticQuery
{
	private String hostname = null;
	private String type = null;
	private String index = null;
	private long from_time = 0;
	private long to_time  = 0;
	public String getHostname()
	{
		return hostname;
	}
	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getIndex()
	{
		return index;
	}
	public void setIndex(String index)
	{
		this.index = index;
	}
	public long getFrom_time()
	{
		return from_time;
	}
	public void setFrom_time(long from_time)
	{
		this.from_time = from_time;
	}
	public long getTo_time()
	{
		return to_time;
	}
	public void setTo_time(long to_time)
	{
		this.to_time = to_time;
	}
	
}
