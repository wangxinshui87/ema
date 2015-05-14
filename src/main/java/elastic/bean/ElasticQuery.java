package elastic.bean;


/**
 * 查询条件类
 * index type为必填字段
 * hostname 标示要查询的主机,可以为null
 * @author pairs
 *
 */
public class ElasticQuery
{
	private String hostname;
	private String type;
	private String index;
	private long startTime;
	private long endTime;
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
	public long getStartTime()
	{
		return startTime;
	}
	public void setStartTime(long startTime)
	{
		this.startTime = startTime;
	}
	public long getEndTime()
	{
		return endTime;
	}
	public void setEndTime(long endTime)
	{
		this.endTime = endTime;
	}

}
