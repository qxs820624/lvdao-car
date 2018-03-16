package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IUserMessageDao;
import com.lvdao.entity.UserMessageEntity;

@Repository("userMessageDao")
public class UserMessageDaoImpl extends BaseDao<UserMessageEntity> implements IUserMessageDao {


}
