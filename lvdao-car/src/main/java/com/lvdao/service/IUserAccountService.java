package com.lvdao.service;

import com.lvdao.entity.UserAccountEntity;

public interface IUserAccountService extends IBaseService<UserAccountEntity> {

	/**
	 * 更改用户账户信息
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 * @param userName
	 * @param accountAmount
	 * @param accountId 账户类型
	 * @param logType 日志类型
	 * @return Map
	 */
	//Map<String, Object> updateAccountAmountByUserName(String userName, BigDecimal accountAmount, String accountId, String logType);
	
	/**
	 * 用户充值后，更新账户信息
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 * @param userName
	 * @return Map
	 */
	 //Map<String, Object> updateCharge(String userName, BigDecimal money, boolean isUpdateRole);
	
	/**
	 * 根据角色权限和比例，分配金钱
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 * @param roleIds
	 * @param money
	 * @param logType 分红类型  20 加权分红  21 经理人分红
	 * @return int
	 */
	 //int updateToAverageMoney(List<String> roleIds, BigDecimal money, String logType);
	
	/**
	 * 根据角色权限和比例，分配金钱
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 * @param roleIds
	 * @param percent
	 * @return int
	 */
	// int updateToAverageIntegral(List<String> roleIds, BigDecimal integral, String logType);
	
	/**
	 * 提升用户等级
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 * @param userName
	 * @return Map
	 */
	 //Map<String, Object> updateAccountType(String userName, RoleEnum roleEnum);
	
	/**
	 * 检测用户是否已经创建金钱和积分账户
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 * @param userName
	 * @return int
	 */
	// int queryAndSaveUserAccount(String userName);
	
	/**
	 * 根据用户名查询用户账户金额或积分
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 * @param userName
	 * @return UserAccountEntity
	 */
	 //UserAccountEntity queryAccountAmountByUserName(String userName, UserAccountTypeEnum userAccountTypeEnum);
	
	/**
	 * 根据用户名，查询用户账户内是否有足够金额
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 * @param userName
	 * @param amount
	 * @return boolean
	 */
	// boolean queryAccountAmountEnoughByUserName(String userName, BigDecimal money, UserAccountTypeEnum userAccountTypeEnum);
	
	/**
	 * 积分定时汇总
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-07 16:19
	 */
	// void updateYesterdayIntegral();
	
	/**
	 * 收益分红操作
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-08 20:22
	 * @param userName
	 * @return
	 */
	 //void updateWithIncome(String userName, BigDecimal money, boolean isUpdateRole);
	
	/**
	 * 提升账号等级并分配收益操作
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-08 20:42
	 * @param userName
	 * @param money
	 * @return
	 */
	 //void updateAccountTypeAndIncome(String userName, BigDecimal money);
	
	/**
	 * 收益分红操作V2
	 * 	测试新版微信支付
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-08 20:22
	 * @param userName
	 * @return
	 */
	//void updateWithIncomeV2(UserOrderEntity userOrder, BigDecimal money);
	
	/**
	 * 提升账号等级并分配收益操作
	 * 	测试新版微信支付
	 * 
	 * @author zhangjianrong
	 * @since 2016-12-08 20:42
	 * @param userName
	 * @param money
	 * @return
	 */
	//void updateAccountTypeAndIncomeV2(UserOrderEntity userOrder, BigDecimal money);
	
}