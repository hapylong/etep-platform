package com.iqb.etep.workflow.design.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.workflow.design.bean.WfProcActReProcdefBean;



public interface IqbWfActReProcdefBeanMapper {
    List<WfProcActReProcdefBean> processList(JSONObject objs);
}