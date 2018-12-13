package com.iqb.etep.workflow.design.dao;
import java.util.List;

import com.iqb.etep.workflow.design.bean.IqbWfProcPropsBean;

public interface IqbWfProcPropsMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(IqbWfProcPropsBean record);
    List<IqbWfProcPropsBean> selectByPrimaryKey(Integer id);
    List<IqbWfProcPropsBean> selectPropertyList(IqbWfProcPropsBean record);
    int updateByPrimaryKeySelective(IqbWfProcPropsBean record);
}