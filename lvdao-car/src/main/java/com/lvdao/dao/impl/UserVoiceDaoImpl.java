package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IUserVoiceDao;
import com.lvdao.entity.UserVoiceEntity;

@Repository("userVoiceDao")
public class UserVoiceDaoImpl extends BaseDao<UserVoiceEntity> implements IUserVoiceDao {
	
}
