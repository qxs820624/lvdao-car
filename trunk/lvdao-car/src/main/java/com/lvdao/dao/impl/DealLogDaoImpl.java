package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.IDealLogDao;
import com.lvdao.entity.DealLogEntity;

@Repository("dealLogDao")
public class DealLogDaoImpl extends BaseDao<DealLogEntity> implements IDealLogDao {

}
