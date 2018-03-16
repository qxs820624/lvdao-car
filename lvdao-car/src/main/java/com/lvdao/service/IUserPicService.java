package com.lvdao.service;

import java.util.List;
import java.util.Map;

import com.lvdao.entity.UserPicEntity;

public interface IUserPicService extends IBaseService<UserPicEntity> {

	/**
	 * 根据用户Id信息查询该用户有多少照片
	 * @param userId
	 * @param picUse 图片用途  0.所有 1.用户头像  2.用户照片 3.其他
	 * @return
	 */
	//int getUserPicCounts(String userId, int picUse);

	/**
	 * 获取用户头像
	 * @param userId
	 * @param picUse 图片用途  0.所有 1.用户头像  2.用户照片 3.其他
	 * @return
	 */
	//List<UserPicEntity> getUserPicByUserId(String userId, int picUse);
	
    /**
     * 根据用户ID修改用户头像
     * @param entity
     * @return
     */
	int updateUserPicByUserId(UserPicEntity entity);
	
	/**
	 * 批量插入
	 * @param entityList
	 * @return
	 */
	int insertList(List<UserPicEntity> entityList);
	
	/**
	 * 批量删除
	 * @param entityList
	 * @return
	 */
	int batchDelete(List<UserPicEntity> entityList);
	
	/**
	 * 
	 * @Description: 根据参数查询用户图片对象
	 * @param  userId
	 * @param  userName
	 * @param  picUse
	 * @return 
	 * @return UserPicEntity
	 * @author:wangyu
	 * @time:2016年10月7日 下午6:47:50
	 */
	//UserPicEntity queryUserPic(String userId, String userName, int picUse);

	/**
	 * 更新用户头像
	 * @param entity
	 * @return
	 */
	Map<String,Object> updateHeadPortrait(String userId, String userName, String headPortrait);

	/**
	 * 获取max groupId
	 * @return
	 */
	int getMaxGroupId();
	
	/**
	 * 根据用户id获取所有相关图片信息
	 * @param userId
	 * @param picUse
	 * @return
	 */
	//List<PictureEntity> getPictureListByUserId(String userId, int picUse);
	
	/**
	 * 根据用户id获取最新的图片信息
	 * @param userId
	 * @param picUse
	 * @return
	 */
	//PictureEntity getPictureByUserId(String userId, int picUse);
	
	/**
	 * 批量上传图片 t_user_pic  t_pic_group t_pic
	 * @param userPics
	 * @param picUse 图片用途
	 * @return
	 */
	//Map<String, Object> uploadPictures(UserPicVO[] userPics, int picUse);
	
}