package com.lvdao.dao.impl;

import org.springframework.stereotype.Repository;

import com.lvdao.dao.ISettlementDao;
import com.lvdao.entity.SettlementEntity;

@Repository("sellermentDao")
public class SettlementDaoImpl extends BaseDao<SettlementEntity> implements ISettlementDao {

}
