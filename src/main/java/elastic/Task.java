package elastic;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import elastic.bean.CpuObject;
import elastic.bean.ElasticQuery;
import elastic.manage.alarm.AlarmService;
import elastic.manage.alarm.impl.AlarmServiceImpl;
import elastic.manage.percolator.PercolateCpuObject;

public class Task extends TimerTask
{
	private ElasticQuery object;
	private boolean isfirstQuery = true;
	private Date date;
	
	/*构造函数设置查询基本条件*/
	public Task()
	{
		date = new Date();
		object = new ElasticQuery();
		object.setHostname("hca1");
		object.setIndex("cpu_all_index-2015.05");
		object.setType("CPU_ALL");
		object.setTo_time(date.getTime());
		object.setFrom_time(date.getTime() - 10*6000);
	}
	public void run()
	{
		AlarmService alarmService = new AlarmServiceImpl();  //用于获取和存储数据
		PercolateCpuObject percolateCpuObject = new PercolateCpuObject(); //用于解析过滤数据
		
		/*第一次查询不用设置时间*/
		if(!isfirstQuery)
		{
			object.setFrom_time(object.getTo_time()); //当前查询开始时间为  为上一次查询的时间
			object.setTo_time(date.getTime());   //查询结束时间为当前时间
		}
		isfirstQuery = false;
		List<CpuObject> cpuObjects;
		try
		{
			cpuObjects = alarmService.getDataFromElatic(object);
			if(null != cpuObjects && !cpuObjects.isEmpty())
			{
				for (CpuObject cpuObject : cpuObjects)
				{
					//System.out.println(cpuObject.getUsed_PCT());		
					cpuObject.setHitList(percolateCpuObject.doPercolateWithCpuObject(cpuObject));
					System.out.println(cpuObject.getHitList());
					if (!cpuObject.getHitList().isEmpty())
					{
							alarmService.saveAlarmData(cpuObject);
					}
				}
			}
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
