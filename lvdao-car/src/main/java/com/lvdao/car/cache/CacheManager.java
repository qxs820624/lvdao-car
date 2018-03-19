package com.lvdao.car.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lvdao.service.IRolePermissionService;



@Service
public class CacheManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheManager.class);

	private static CacheManager instance = new CacheManager();
	
	private CacheManager() {
		
	}
	
	public static CacheManager getInstance(){
        return instance;
    }
	
	/**
	 * 角色权限Service
	 */
	@Autowired
	private IRolePermissionService rolePermissionService;
	
	
	@Scheduled(cron = "0/50 * * * * ?")
	public void loadUserPermissionCache() {
		LOGGER.info("Begin To Load User Permission cache.。。");
		rolePermissionService.saveAllUserPermissionInSession();
		LOGGER.info("Load Permission Cache Success.");
	}
	
	
}
	

