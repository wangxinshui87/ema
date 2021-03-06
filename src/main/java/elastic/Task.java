package elastic;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import elastic.bean.CpuObject;
import elastic.bean.ElasticQuery;
import elastic.manage.alarm.AlarmService;
import elastic.manage.alarm.impl.AlarmServiceImpl;
import elastic.manage.percolator.PercolatorService;
import elastic.manage.percolator.impl.PercolatorServiceImpl;

public class Task extends TimerTask
{
	private ElasticQuery object;
	private boolean isFirstQuery = true;
	
	
	/*构造函数设置查询基本条件*/
	public Task()
	{
		Date date = new Date();
		object = new ElasticQuery();
		//object.setHostname("hca1");
		object.setIndex("cpu_all_index-2015.05");
		object.setType("CPU_ALL");
		object.setEndTime(date.getTime());
		object.setStartTime(date.getTime() - 10*6000);
	}
	public void run()
	{
		Date date = new Date();
		AlarmService alarmService = new AlarmServiceImpl();  //用于获取和存储数据
		PercolatorService percolateCpuObject = new PercolatorServiceImpl(); //用于解析过滤数据
		
		/*第一次查询不用设置时间*/
		if(!isFirstQuery)
		{
			object.setStartTime(object.getEndTime()); //当前查询开始时间为  为上一次查询的时间
			//System.out.println("上次结束时间:" + object.getEndTime());
			object.setEndTime(date.getTime());   //查询结束时间为当前时间
		//	System.out.println("现在的时间:" + date.getTime());
		}
		isFirstQuery = false;
		List<CpuObject> cpuObjects;
		try
		{
			cpuObjects = alarmService.getDataFromElatic(object);
			if(null != cpuObjects && !cpuObjects.isEmpty())
			{
				//System.out.println("开始过滤");
				for (CpuObject cpuObject : cpuObjects)
				{
					//System.out.println(cpuObject.getUsedPct());		
					cpuObject.setHitList(percolateCpuObject.doPercolatorService(cpuObject));
				//	System.out.println(cpuObject.getHitList());
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
