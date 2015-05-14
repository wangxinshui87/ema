package elastic.manage.alarm.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lang3.StringUtils;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import elastic.bean.CpuObject;
import elastic.bean.ElasticObject;
import elastic.bean.ElasticQuery;
import elastic.manage.alarm.AlarmService;

public class AlarmServiceImpl implements AlarmService
{
	private String clusterName = "alarm"; // 索引名字
	private String ipAddress = "172.16.8.152";
	private Client client; // 节点
	
	public AlarmServiceImpl()
	{
		/* 设置以及建立节点 */
		Builder settings = ImmutableSettings.settingsBuilder().put(
				"cluster.name", this.clusterName);
		this.client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						this.ipAddress, 9300));
	}
	
	
	public List<CpuObject> getDataFromElatic(ElasticQuery object) throws Exception
	{
		if (null == object || StringUtils.isEmpty(object.getIndex())
				|| StringUtils.isEmpty(object.getType()))
		{
			throw new Exception("参数不能为空");
		}
		SearchResponse actionGet;
		/*判断hostname是否为空*/
		if (!object.getHostname().isEmpty())
		{
					 actionGet = client
					.prepareSearch(object.getIndex())
					.setTypes(object.getType())
					.setQuery(
							QueryBuilders
									.boolQuery()
									.must(QueryBuilders.termQuery("hostname",
											object.getHostname()))
									.must(QueryBuilders.rangeQuery("@timestamp")
											.from(object.getFrom_time())
											.to(object.getTo_time())))
											.execute()
											.actionGet();
		}
		else
		{
					actionGet = client
					.prepareSearch(object.getIndex())
					.setTypes(object.getType())
					.setQuery(
							QueryBuilders
									.boolQuery()
									.must(QueryBuilders.rangeQuery("@timestamp")
											.from(object.getFrom_time())
											.to(object.getTo_time())))
											.execute()
											.actionGet();
		}
		
		SearchHits hits = actionGet.getHits();
		// List<Map<String, Object>> matchRsult = new LinkedList<Map<String,
		// Object>>();
		List<CpuObject> matchRsult = new ArrayList<CpuObject>(); // 存放match消息

		for (SearchHit hit : hits.getHits())
		{
			CpuObject tempCpuObject = new CpuObject();
			String hostname = (String) hit.getSource().get("hostname");
			double wait_PCT = (Double) hit.getSource().get("Wait_PCT");
			double sys_PCT = (Double) hit.getSource().get("Sys_PCT");
			double user_PCT = (Double) hit.getSource().get("User_PCT");
			double used_PCT = wait_PCT + sys_PCT + user_PCT;
			/* 设置返回消息 */
			tempCpuObject.setIndex(object.getIndex());
			tempCpuObject.setHostname(object.getHostname());
			tempCpuObject.setSys_PCT(sys_PCT);
			tempCpuObject.setWait_PCT(wait_PCT);
			tempCpuObject.setUser_PCT(user_PCT);
			tempCpuObject.setUsed_PCT(used_PCT);
			matchRsult.add(tempCpuObject);
		//	System.out.println("有数据出来");
		}
		return matchRsult;
	}

	public void saveAlarmData(CpuObject cpuobject) throws Exception
	{
		if (null == cpuobject || (cpuobject.getHitList().size() == 0 ))
		{
			try
			{
				throw new Exception("参数不能为空");
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		IndexResponse response = client.prepareIndex("aaa", "cpu")
				.setSource(XContentFactory.jsonBuilder()
				.startObject()
					.field("Wait_PCT", cpuobject.getWait_PCT())
					.field("Sys_PCT", cpuobject.getSys_PCT())
					.field("User_PCT", cpuobject.getUser_PCT())
					.field("Used_PCT", cpuobject.getUsed_PCT())
					.field("index",cpuobject.getIndex())
					.field("ID",cpuobject.getHitList())
				.endObject())
				.setTTL(8000)
				.execute().actionGet();
		
	}

}
