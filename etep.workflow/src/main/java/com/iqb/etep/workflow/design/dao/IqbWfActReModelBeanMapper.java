package com.iqb.etep.workflow.design.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.workflow.design.bean.WfProcActReModelBean;

public interface IqbWfActReModelBeanMapper {

    int deleteByPrimaryKey(String id);

    WfProcActReModelBean selectByPrimaryKey(String id);
    
    List<WfProcActReModelBean> selectModelList(JSONObject objs);
    
}