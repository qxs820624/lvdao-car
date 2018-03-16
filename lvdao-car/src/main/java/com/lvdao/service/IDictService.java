package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.DictEntity;

/**
 * 数据字典服务service
 * 
 * date: 2016年9月6日 下午8:06:39 
 * @author wangyu
 */
public interface IDictService extends IBaseService<DictEntity> {

	Map<String, Object> queryDictListByDictGroupId(String dictGroupId,String UserId);
    
	/**
	 * 
	 * @Description:根据字典ID查询字段对象信息(先查询mongo如何查询不到在查询mysql)
	 * @param  dictId
	 * @return 
	 * @return DictEntity
	 * @author:wangyu
	 * @time:2016年10月10日 下午10:08:21
	 */
	//DictEntity queryDictByMongoAndMysql(String dictId);

	/**
	 * 获取移动端最新的版本号，通过参数来控制是安卓 还是 ios by zhaoming
	 * @param dictGroupId
	 * @return
	 */
	//List<DictEntity> getNewVersion(String dictGroupId);
	
	DictEntity queryDitcValueByDictId(String dictId);

	List<DictEntity> queryDictGroupList(Map<String, Object> map);
}
