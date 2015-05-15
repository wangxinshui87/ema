package elastic.manage.alarm.impl;

import java.util.ArrayList;
import java.util.Date;
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

	public List<CpuObject> getDataFromElatic(ElasticQuery object)
			throws Exception
	{
		if (null == object || StringUtils.isEmpty(object.getIndex())
				|| StringUtils.isEmpty(object.getType()))
		{
			throw new Exception("参数不能为空");
		}
		SearchResponse actionGet;
		/* 判断hostname是否为空 */
		if (object.getHostname() != null )
		{
			actionGet = client
					.prepareSearch(object.getIndex())
					.setTypes(object.getType())
					.setQuery(
							QueryBuilders
									.boolQuery()
									.must(QueryBuilders.termQuery("hostname",
											object.getHostname()))
									.must(QueryBuilders
											.rangeQuery("@timestamp")
											.from(object.getStartTime())
											.to(object.getEndTime())))
					.execute().actionGet();
		}
		else
		{
			//System.out.println("in no hostname");
			actionGet = client
					.prepareSearch(object.getIndex())
					.setTypes(object.getType())
					.setQuery(
							QueryBuilders.boolQuery().must(
									QueryBuilders.rangeQuery("@timestamp")
											.from(object.getStartTime())
											.to(object.getEndTime())))
					.execute().actionGet();
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
			String ZZZZ = (String) hit.getSource().get("ZZZZ");
			Date timestamp = (Date) hit.getSource().get("@timestamp");
			
		//	System.out.println(used_PCT);
			/* 设置返回消息 */
			tempCpuObject.setIndex(object.getIndex());
			tempCpuObject.setHostname(hostname);
			tempCpuObject.setSysPct(sys_PCT);
			tempCpuObject.setWaitPct(wait_PCT);
			tempCpuObject.setUserPct(user_PCT);
			tempCpuObject.setUsedPct(used_PCT);
			tempCpuObject.setZZZZ(ZZZZ);
			matchRsult.add(tempCpuObject);
		//	System.out.println("有数据出来");
		//	System.out.println(hostname);
		}
		return matchRsult;
	}

	public void saveAlarmData(CpuObject cpuobject) throws Exception
	{
		if (null == cpuobject || (cpuobject.getHitList().size() == 0))
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
		//System.out.println("开始存储数据");
		IndexResponse response = client
				.prepareIndex("alarms", "cpu")
				.setSource(
						XContentFactory.jsonBuilder().startObject()
								.field("Wait_PCT", cpuobject.getWaitPct())
								.field("Sys_PCT", cpuobject.getSysPct())
								.field("User_PCT", cpuobject.getUserPct())
								.field("Used_PCT", cpuobject.getUsedPct())
								.field("index", cpuobject.getIndex())
								.field("athresholdIds", cpuobject.getHitList())
								.field("hostname", cpuobject.getHostname())
								.field("ZZZZ",cpuobject.getZZZZ())
								.field("@timestamp",cpuobject.getTimestamp())
								.endObject()).setTTL(8000).execute()
				.actionGet();

	}

}
