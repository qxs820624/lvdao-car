package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IUserVideoDao;
import com.lvdao.entity.UserViedoEntity;

@Repository("userVideoDao")
public class UserVideoDaoImpl extends BaseDao<UserViedoEntity> implements IUserVideoDao {
	
}
