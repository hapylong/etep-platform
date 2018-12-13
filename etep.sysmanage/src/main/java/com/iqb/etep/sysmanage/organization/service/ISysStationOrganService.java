package com.iqb.etep.sysmanage.organization.service;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.exception.IqbSqlException;

public interface ISysStationOrganService {
	public void insertSysStationOrgan(JSONObject objs)throws IqbException, IqbSqlException;

}
