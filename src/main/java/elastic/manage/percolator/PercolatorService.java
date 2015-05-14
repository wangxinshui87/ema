package elastic.manage.percolator;

import java.util.List;

import elastic.bean.CpuObject;

public interface PercolatorService
{
	public List<String> doPercolateWithCpuObject(CpuObject cpuObject) throws Exception;
}
