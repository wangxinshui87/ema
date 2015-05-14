package elastic.manage.percolator;

import java.util.List;

import elastic.bean.ElasticObject;

public interface PercolatorService
{
	/**
	 * 
	 * @param elasticObject
	 * 参数为搜索出的数据用来过滤
	 * @return
	 * athresholdIds //匹配到的规则ID
	 * @throws Exception
	 */
	public List<String> doPercolatorService(ElasticObject elasticObject) throws Exception;
}
