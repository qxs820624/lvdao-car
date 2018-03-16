package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IDriverPassengerDao;
import com.lvdao.entity.DriverPassengerEntity;

@Repository("driverPassengerDao")
public class DriverPassengerDaoImpl extends BaseDao<DriverPassengerEntity> implements IDriverPassengerDao {

}
