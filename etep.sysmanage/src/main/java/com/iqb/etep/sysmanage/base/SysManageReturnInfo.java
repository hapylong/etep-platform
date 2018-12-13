package com.iqb.etep.sysmanage.base;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.iqb.etep.common.base.CommonReturnInfo;
import com.iqb.etep.common.base.IReturnInfo;

/**
 * 
 * Description: 系统返回码
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月31日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
public enum SysManageReturnInfo implements IReturnInfo{
    
    
    /** 登录相关异常0101  **/
    SYS_LOGIN_01010001("01010001", "用户名或密码错误", "用户名或密码错误"),//用户名或密码错误
    SYS_LOGIN_01010002("01010002", "用户被冻结", "用户被冻结"),
    SYS_LOGIN_01010003("01010003", "不存在此用户", "用户名或密码错误"),
    SYS_LOGIN_01010004("01010004", "输入密码错误次数超过5次", "您输入的密码错误次数过多，请30分钟之后再试"),
    SYS_LOGIN_01010005("01010005", "验证码错误", "验证码错误"),
  
    
    /** ######## 系统管理模块响应码定义:01****** ######## END **/

    /** 用户相关 0102 **/
    SYS_USER_01020001("01020001", "用户编码已存在", "用户编码已存在"),
    SYS_USER_01020002("01020002", "密码输入错误", "密码输入错误"),
    SYS_USER_01020003("01020003", "邮箱错输入误", "邮箱输入错误"),
    SYS_USER_01020004("01020004", "用户名为空", "用户名为空"),
    SYS_USER_01020005("01020005", "格式校验失败", "格式校验失败"),
    SYS_USER_01020006("01020006", "用户编码为空", "用户编码为空"),
    SYS_USER_01020007("01020007", "用户信息完整性校验失败", "用户信息完整性校验失败"),   
    SYS_USER_0102008("01020008","该角色权限已被用户分配确定要删除","该角色权限已被用户分配确定要删除"),
    SYS_USER_0102009("01020009","用户编码不存在","用户编码不存在"),
    SYS_USER_0102010("01020010","机构为空","机构为空"),
    SYS_USER_0102011("01020011","真实姓名为空","真实姓名为空"),
    SYS_USER_0102012("01020012","电话为空","电话为空"),
    SYS_USER_0102013("01020013","邮箱为空","邮箱为空"),
    SYS_USER_0102014("01020014","状态为空","状态为空"),
    
    /**岗位相关的0103**/
    SYS_STATION_01030001("01030001","角色编码已存在","角色编码已存在"),
    SYS_STATION_01030002("01030002","角色名称已存在","角色名称已存在"),
    SYS_STATION_01030003("01030003","角色名称不存在","角色名称不存在"),
    SYS_STATION_01030004("01030004","角色信息完整性校验失败","角色信息完整性校验失败"),
    SYS_STATION_01030005("01030005","该角色已被用户使用,无法删除","该角色已被用户使用，无法删除"),
    SYS_STATION_0103006("0103006","该角色已分配机构,无法删除","该角色已分配机构,无法删除"),
    SYS_STATION_O103007("0103007","该角色数据异常","角色ID不存在"),
    SYS_STATION_O103008("0103008","该机构下没有分配角色","该机构下没有分配角色"),
    SYS_STATION_O103009("0103008","传入参数校验失败","保存失败"),
    
    
    /**机构相关0104**/
    SYS_STATION_01040001("01040001","机构名称已存在", "机构名称已存在"),
    SYS_STATION_01040002("01040002","机构数据不存在", "机构数据不存在"),
    SYS_STATION_01040003("01040003","机构数据信息完整性校验失败", "机构数据信息完整性校验失败"),
    SYS_STATION_01040004("01040004","该机构为根机构，禁止删除", "该机构为根机构，禁止删除"),
    SYS_STATION_01040005("01040005","该机构存在子级机构，无法删除", "该机构存在子级机构，无法删除"),
    SYS_STATION_01040006("01040006","该机构已被角色使用，无法删除", "该机构已被角色使用，无法删除"),
    SYS_STATION_01040007("01040007","该机构已被用户使用，无法删除", "该机构已被用户使用，无法删除"),
    SYS_STATION_01040008("01040008","取消勾选菜单已被该机构下属角色授权，无法取消", "取消勾选菜单已被该机构下属角色授权，无法取消"),
    
    /**部门相关0109**/
    SYS_STATION_01090001("01090001","部门名称已存在", "部门名称已存在"),
    SYS_STATION_01090002("01090002","部门数据不存在", "部门数据不存在"),
    SYS_STATION_01090003("01090003","部门数据信息完整性校验失败", "部门数据信息完整性校验失败"),
    SYS_STATION_01090004("01090004","该部门已被用户使用，无法删除", "该部门已被用户使用，无法删除"),
    
    /**菜单相关0105**/
    SYS_STATION_01050001("01050001","菜单名称已存在", "菜单名称已存在"),
    SYS_STATION_01050002("01050002","菜单数据不存在", "菜单数据不存在"),
    SYS_STATION_01050003("01050003","菜单数据信息完整性校验失败", "菜单数据信息完整性校验失败"),
    SYS_STATION_01050004("01050004","该菜单存在子级菜单，无法删除", "该菜单存在子级菜单，无法删除"),
    SYS_STATION_01050005("01050005","该菜单已被授权，无法删除", "该菜单已被授权，无法删除"),
    
    /**字典类别相关0106**/
    SYS_STATION_01060001("01060001","字典类别代码已存在", "字典类别代码已存在"),
    SYS_STATION_01060002("01060002","字典类别数据不存在", "字典类别数据不存在"),
    SYS_STATION_01060003("01060003","字典类别数据信息完整性校验失败", "字典类别数据信息完整性校验失败"),
    SYS_STATION_01060004("01060004","字典类别数据已停用", "字典类别数据已停用"),
    
    /**字典项相关0107**/
    SYS_STATION_01070001("01070001","字典项代码已存在", "字典项代码已存在"),
    SYS_STATION_01070002("01070002","字典项数据不存在", "字典项数据不存在"),
    SYS_STATION_01070003("01070003","字典项数据信息完整性校验失败", "字典项数据信息完整性校验失败"),
    
    /**参数相关0108**/
    SYS_STATION_01080001("01080001","参数代码已存在", "参数代码已存在"),
    SYS_STATION_01080002("01080002","参数数据不存在", "参数数据不存在"),
    SYS_STATION_01080003("01080003","参数数据信息完整性校验失败", "参数数据信息完整性校验失败"),
    SYS_STATION_01080004("01080004","参数数据已停用", "参数数据已停用"),
   
    /** 权限相关  **/
    SYS_AUTH_01090001("01090001", "用户没有该菜单权限", "您没有该菜单权限"),
    SYS_CRM_PUSH_01091002("01091002", "机构推送crm数据信息为空", "机构推送crm数据信息为空"),
    SYS_CRM_PUSH_01091003("01091003", "机构推送crm数据完整性校验失败", "机构推送crm数据完整性校验失败"),
    SYS_CRM_PUSH_01091004("01091004", "crm推送至其他系统代码抛异常", "推送失败，网络异常");
    
    /** 响应代码 **/
    private String retCode = "";
    
    /** 提示信息-用户提示信息 **/
    private String retUserInfo = "";
    
    /** 响应码含义-实际响应信息 **/
    private String retFactInfo = "";
        
    /** 
     * @param retCode 响应代码
     * @param retFactInfo 响应码含义-实际响应信息 
     * @param retUserInfo  提示信息-用户提示信息
     */
    private SysManageReturnInfo(String retCode, String retFactInfo, String retUserInfo) {
        this.retCode = retCode;
        this.retFactInfo = retFactInfo;
        this.retUserInfo = retUserInfo;
    }

    @Override
    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    @Override
    public String getRetFactInfo() {
        return retFactInfo;
    }

    public void setRetFactInfo(String retFactInfo) {
        this.retFactInfo = retFactInfo;
    }

    @Override
    public String getRetUserInfo() {
        return retUserInfo;
    }

    public void setRetUserInfo(String retUserInfo) {
        this.retUserInfo = retUserInfo;
    }
    
    /**
     * 通过响应代码 获取对应的ReturnInfo
     * @param retCode-返回码
     * @return 响应枚举类型
     */
    @Override
    public IReturnInfo getReturnCodeInfoByCode(IReturnInfo returnInfo) {
        if (map.get(returnInfo.getRetCode()) != null) {
            return map.get(returnInfo.getRetCode());
        } else {
            return CommonReturnInfo.BASE00000099;
        }
    }
    
    /**
     * 重写toString
     */
    @Override
    public String toString() {
        return new StringBuffer("{retCode:").append(retCode)
                .append(";retFactInfo(实际响应信息):").append(retFactInfo)
                .append(";retUserInfo(客户提示信息):").append(retUserInfo).append("}").toString();
    }

    /**存放全部枚举的缓存对象*/
    private static Map<String,SysManageReturnInfo> map = new HashMap<>();
    
    /**将所有枚举缓存*/
    static{
        EnumSet<SysManageReturnInfo> currEnumSet = EnumSet.allOf(SysManageReturnInfo.class);
        
        for (SysManageReturnInfo retCodeType : currEnumSet) {
            map.put(retCodeType.getRetCode(), retCodeType);
        }
    }

}
