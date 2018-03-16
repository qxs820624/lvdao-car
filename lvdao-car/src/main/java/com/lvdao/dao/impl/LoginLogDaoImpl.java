package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.ILoginLogDao;
import com.lvdao.entity.LoginLogEntity;

@Repository("loginLogDao")
public class LoginLogDaoImpl extends BaseDao<LoginLogEntity> implements ILoginLogDao {

}
