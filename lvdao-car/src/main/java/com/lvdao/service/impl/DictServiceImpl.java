package com.lvdao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvdao.common.CommonConst;
import com.lvdao.common.pagination.Page;
import com.lvdao.common.pagination.PageList;
import com.lvdao.dao.IDictDao;
import com.lvdao.entity.DictEntity;
import com.lvdao.entity.UserEntity;
import com.lvdao.service.IDictService;
import com.lvdao.service.IUserService;

@Service("dictService")
public class DictServiceImpl implements IDictService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);
	
	@Autowired
	private IDictDao dictDao;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public int insert(DictEntity entity) {
		return dictDao.insert(entity);
	}
	
	@Override
	public int update(DictEntity entity) {
		return dictDao.update(entity);
	}
	
	@Override
	public int delete(DictEntity entity) {
		return dictDao.delete(entity);
	}
	
	@Override
	public List<DictEntity> queryList(Map<String, Object> map) {
		return dictDao.queryList(map);
	}
	
	@Override
	public List<DictEntity> queryAll() {
		return dictDao.queryAll();
	}
	
	@Override
	public PageList<DictEntity> queryPage(Page page, Map<String, Object> map) {
		if(null == page || null == map) {
			LOGGER.info("IDictService queryPage page or map is null.");
			return null;
		}
		LOGGER.info("Entering IDictService queryPage service...");
		PageList<DictEntity> list = dictDao.queryPage(page, map);
		LOGGER.info("Exiting IDictService queryPage service...");
		return list;
	}

	@Override
	public int getMaxId() {
		return dictDao.getMaxId();
	}
	
	/**
	 * 根据字典组ID获取字典集合信息,如系统配置参数
	 * 版本更新
	 * 货车基本信息
	 * @param dictGroupId
	 * @return
	 */
	@Override
	public Map<String, Object> queryDictListByDictGroupId(String dictGroupId,String UserId) {
		LOGGER.info("Entering IDictService queryDictListByDictGroupId...  dictGroupId = :{} ", dictGroupId);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(StringUtils.isEmpty(dictGroupId)){
//			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
//			resultMap.put(CommonConst.RESPONSE_MESSAGE, "系统参数的组Id不能为空");
//			return resultMap;
			//如果参数为空返回全部
			//List<DictEntity> queryAll = this.queryAll();
//			if(queryAll==null || queryAll.size()==CommonConst.DIGIT_ZERO){
//				resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
//				resultMap.put(CommonConst.RESPONSE_MESSAGE, "请联系管理员，未查询到任何数据");
//				return resultMap;
//			}else{
				Map<String, Object> data = this.getBaseInfoList();
				resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
				resultMap.put(CommonConst.RESPONSE_MESSAGE, "查询成功");
				resultMap.put("data", data);
				LOGGER.info("Exiting IDictService queryDictListByDictGroupId service...");
				return resultMap;
//			}
		}
		if (StringUtils.isNotEmpty(UserId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", UserId);
			List<UserEntity> userEntities = userService.queryList(map);
			UserEntity userEntity = userEntities.get(0);
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "查询用户成功");
			resultMap.put("data", userEntity);
		}
		
		//安卓返回信息
		if(dictGroupId.equals("andriod_version")){
			//版本信息
			Map<String,Object> andriod_version_map = new HashMap<String,Object>();
			andriod_version_map.put("andriod_version_name", getDictValueOne( "andriod_version", "andriod_version_name"));
			andriod_version_map.put("andriod_version_desc", getDictValueOne( "andriod_version", "andriod_version_desc"));
			andriod_version_map.put("andriod_version_code", getDictValueOne( "andriod_version", "andriod_version_code"));
			andriod_version_map.put("andriod_version_url", getDictValueOne( "andriod_version", "andriod_version_url"));
			andriod_version_map.put("andriod_version_update", getDictValueOne( "andriod_version", "andriod_version_update"));
			
			resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
			resultMap.put(CommonConst.RESPONSE_MESSAGE, "查询安卓版本信息成功");
			resultMap.put("data", andriod_version_map);
			LOGGER.info("Exiting IDictService queryDictListByDictGroupId service...");
			return resultMap;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dictGroupId", dictGroupId);
		try {
			List<DictEntity> queryList = this.queryList(map);
			if(queryList==null || queryList.size()==CommonConst.DIGIT_ZERO){
				resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_FAIL);
				resultMap.put(CommonConst.RESPONSE_MESSAGE, "请确定传入的系统参数的组ID是否正确");
				return resultMap;
			}
				resultMap.put(CommonConst.RESPONSE_STATUS, CommonConst.RESPONSE_STATUS_SUCCESS);
				resultMap.put(CommonConst.RESPONSE_MESSAGE, "查询成功");
				resultMap.put("queryList", queryList);
				LOGGER.info("Exiting IDictService queryDictListByDictGroupId service...");
				return resultMap;
		} catch (Exception e) {
			throw new RuntimeException("查询异常");
		}
	}
	
	
	public String getDictValueOne(String dictGroupId , String dictId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dictGroupId", dictGroupId);
		map.put("dictId", dictId);
		try {
			List<DictEntity> queryList = this.queryList(map);
			if(queryList!=null && queryList.size()>CommonConst.DIGIT_ZERO){
				return queryList.get(0).getDictValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	} 
	
	//1 小面包车  2中面包车  3小货车 4中货车
	public Map<String,Object> getBaseInfoList(){
		//最终返回数据
		Map<String,Object> data  = new HashMap<String,Object>();
		
		//小货车信息
		Map<String,Object> small_truck_map  = new HashMap<String,Object>();
		small_truck_map.put("small_truck_lwh", getDictValueOne( CommonConst.SMALL_TRUCK_BASE_INFO, "small_truck_lwh"));
		small_truck_map.put("small_truck_load", getDictValueOne( CommonConst.SMALL_TRUCK_BASE_INFO, "small_truck_load"));
		small_truck_map.put("small_truck_volume", getDictValueOne( CommonConst.SMALL_TRUCK_BASE_INFO, "small_truck_volume"));
		small_truck_map.put("vehicle_level", CommonConst.DIGIT_THREE);
		
		//中货车信息
		Map<String,Object> mid_truck_map  = new HashMap<String,Object>();
		mid_truck_map.put("mid_truck_lwh", getDictValueOne( CommonConst.MID_TRUCK_BASE_INFO, "mid_truck_lwh"));
		mid_truck_map.put("mid_truck_load", getDictValueOne( CommonConst.MID_TRUCK_BASE_INFO, "mid_truck_load"));
		mid_truck_map.put("mid_truck_volume", getDictValueOne( CommonConst.MID_TRUCK_BASE_INFO, "mid_truck_volume"));
		mid_truck_map.put("vehicle_level", CommonConst.DIGIT_FOUR);
		
		//小面包车信息
		Map<String,Object> small_minbus_map = new HashMap<String,Object>();
		small_minbus_map.put("small_minibus_lwh", getDictValueOne( CommonConst.SMALL_MINIBUS_BASE_INFO, "small_minibus_lwh"));
		small_minbus_map.put("mid_truck_load", getDictValueOne( CommonConst.SMALL_MINIBUS_BASE_INFO, "small_minibus_load"));
		small_minbus_map.put("small_minibus_volume", getDictValueOne( CommonConst.SMALL_MINIBUS_BASE_INFO, "small_minibus_volume"));
		small_minbus_map.put("vehicle_level", CommonConst.DIGIT_ONE);
		
		//中面包信息
		Map<String,Object> mid_minbus_map = new HashMap<String,Object>();
		mid_minbus_map.put("mid_minibus_lwh", getDictValueOne( CommonConst.MID_MINIBUS_BASE_INFO, "mid_minibus_lwh"));
		mid_minbus_map.put("mid_minibus_load", getDictValueOne( CommonConst.MID_MINIBUS_BASE_INFO, "mid_minibus_load"));
		mid_minbus_map.put("mid_minibus_volume", getDictValueOne( CommonConst.MID_MINIBUS_BASE_INFO, "mid_minibus_volume"));
		mid_minbus_map.put("vehicle_level", CommonConst.DIGIT_TWO);
		
		//版本信息
		Map<String,Object> andriod_version_map = new HashMap<String,Object>();
		andriod_version_map.put("andriod_version_name", getDictValueOne( "andriod_version", "andriod_version_name"));
		andriod_version_map.put("andriod_version_desc", getDictValueOne( "andriod_version", "andriod_version_desc"));
		andriod_version_map.put("andriod_version_code", getDictValueOne( "andriod_version", "andriod_version_code"));
		andriod_version_map.put("andriod_version_url", getDictValueOne( "andriod_version", "andriod_version_url"));
		andriod_version_map.put("andriod_version_update", getDictValueOne( "andriod_version", "andriod_version_update"));
		
		//ios信息
		Map<String,Object> ios_version_map = new HashMap<String,Object>();
		ios_version_map.put("ios_version_name", getDictValueOne( "ios_version", "ios_version_name"));

		//百度鹰眼服务id
		Map<String, Object> baiduMapServiceMap = new HashMap<String, Object>();
		baiduMapServiceMap.put("baidu_map_service_id", getDictValueOne("baidu_map","baidu_map_service_id"));
		
		//结果
		data.put("small_truck_map", small_truck_map);
		data.put("mid_truck_map", mid_truck_map);
		data.put("small_minbus_map", small_minbus_map);
		data.put("mid_minbus_map", mid_minbus_map);
		
		//安卓 ios信息
		data.put("andriod_version_map", andriod_version_map);
		data.put("ios_version_map", ios_version_map);
		
		data.put("baidu_map_service_map", baiduMapServiceMap);
		return data;
	}

	@Override
	public DictEntity queryDitcValueByDictId(String dictId) {
		return dictDao.queryDitcValueByDictId(dictId);
	}

	@Override
	public List<DictEntity> queryDictGroupList(Map<String, Object> map) {
		return dictDao.queryDictGroupList(map);
	}
	

}
