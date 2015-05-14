package elastic.manage.alarm;

import java.util.List;

import elastic.bean.CpuObject;
import elastic.bean.ElasticObject;
import elastic.bean.ElasticQuery;

public interface AlarmService
{
	/**
	 * 
	 * @param object:查询条件的对象,object中要设置包括hostname,index,type.(要查询的索引号,数据类型,以及那台机器上的)
	 * @return 返回查询到的一个List<CpuObject>,里面存储符合条件的数据
	 */
	public List<CpuObject> getDataFromElatic(ElasticQuery object) throws Exception;
	
	/**
	 * 
	 * @param cpuobject 过滤后构造的cpuobject
	 * 用来把匹配到的规则,消息插入到elastic上
	 */
	public void saveAlarmData(CpuObject cpuobject) throws Exception;
}
