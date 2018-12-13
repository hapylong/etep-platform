package com.iqb.etep.sysmanage.login.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.annotation.ConcernProperty.ConcernActionScope;
import com.iqb.etep.common.exception.IqbException;
import com.iqb.etep.common.utils.DatePattern;
import com.iqb.etep.common.utils.DateUtil;
import com.iqb.etep.common.utils.JSONUtil;
import com.iqb.etep.common.utils.ObjCheckUtil;
import com.iqb.etep.common.utils.StringUtil;
import com.iqb.etep.common.utils.SysUserSession;
import com.iqb.etep.sysmanage.base.SysManageAttr.SysRedisConst;
import com.iqb.etep.sysmanage.login.biz.SysLoginBiz;
import com.iqb.etep.sysmanage.login.service.ISysLoginService;
import com.iqb.etep.sysmanage.user.bean.SysUser;

/**
 * 
 * Description: 系统用户登录
 * @author wangxinbang
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2016年8月15日    wangxinbang       1.0        1.0 Version 
 * </pre>
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class SysLoginService implements ISysLoginService{
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SysLoginBiz sysLoginBiz;
    
    /**
     * 用户session
     */
    @Autowired
    private SysUserSession sysUserSession;

    @Override
    public Object sysUserLogin(JSONObject objs) throws IqbException, Exception {
        /** 检查数据完整性  **/
        SysUser sysUser = JSONUtil.toJavaObject(objs, SysUser.class);
        ObjCheckUtil.checkConcernProperty(sysUser, ConcernActionScope.Login);
        String pwd = sysUser.getUserPassword();
        
        sysUser.setUserPassword(pwd);
        sysUser = sysLoginBiz.getUserByUserCode(sysUser);
        
        /** 缓存用户信息  **/
        sysUserSession.setSysUserSession(sysUser);
        logger.info("用户" + sysUserSession.getUserCode() + "登录");
        return sysUser;
    }
    
    @Override
    public void sysUserLogout() throws IqbException {
        logger.info("用户" + sysUserSession.getUserCode() + "退出登录");
        sysUserSession.cancelSysUserSession();
    }

    @Override
    public Map getSysLoginInfo()  throws IqbException, Exception {
        Map retMap = new HashMap();
        retMap.put("userCode", sysUserSession.getUserCode());
        retMap.put("orgName", sysUserSession.getOrgName());
        retMap.put("orgShortName", sysUserSession.getOrgShortName());
        retMap.put("sysDate", DateUtil.toString(new Date(), DatePattern.DATEPARTTEN_YYYYMMDD_TYPE3));
        retMap.put("sysStatus", this.getSysStatus(sysLoginBiz.getSysCurrStatus()));
        return retMap;
    }
    
    /**
     * 
     * Description: 获取系统当前状态
     * @param
     * @return String
     * @throws
     * @Author wangxinbang
     * Create Date: 2016年9月18日 下午6:29:07
     */
    private String getSysStatus(String sysStatus){
        if(StringUtil.isEmpty(sysStatus)){
            return "停用";
        }
        String retStr = "";
        switch (sysStatus) {
            case SysRedisConst.SYS_STATUS_NORMAL:
                retStr = "正常";
                break;
            case SysRedisConst.SYS_STATUS_DISABLE:
                retStr = "停用";
                break;
            case SysRedisConst.SYS_STATUS_PARTIALLY_DISABLE:
                retStr = "部分停用";
                break;
            default:
                break;
        }
        return retStr;
    }

    private static String codeChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public void get2DCaptcha(String key, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获得验证码集合的长度
        int charsLength = codeChars.length();
        // 这3条语句都可以关闭浏览器的缓冲区，但是由于浏览器的版本不同，对这3条语句的支持也不同
        // 因此，为了保险起见，同时使用这3条语句来关闭浏览器的缓冲区
        response.setHeader("ragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 设置图形验证码的长和宽
        int width = 90, height = 30;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandomColor(180, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, height));
        g.setColor(getRandomColor(120, 180));
        // 用户保存最后随机生成的验证码
        StringBuffer validationCode = new StringBuffer();
        // 验证码的随机字体
        String[] fontNames = {"Times New Roman", "Book antiqua", "Arial"};
        // 随机生成4个验证码
        for (int i = 0; i < 4; i++) {
            // 随机设置当前验证码的字符的字体
            g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC,
                    height));
            // 随机获得当前验证码的字符
            char codeChar = codeChars.charAt(random.nextInt(charsLength));
            validationCode.append(codeChar);
            // 随机设置当前验证码字符的颜色
            g.setColor(getRandomColor(10, 100));
            // 在图形上输出验证码字符，x和y都是随机生成的
            g.drawString(String.valueOf(codeChar), 16 * i + random.nextInt(7),
                    height - random.nextInt(6));
        }
        // 获得HttpSession对象
        HttpSession session = request.getSession();
        // 设置session对象5分钟失效
        session.setMaxInactiveInterval(5 * 60);
        // 将验证码保存在session对象中,key为validation_code
        session.setAttribute(key, validationCode.toString());
        // 关闭Graphics对象
        g.dispose();
        OutputStream outS = response.getOutputStream();
        ImageIO.write(image, "JPEG", outS);
    }

    private Color getRandomColor(int minColor, int maxColor) {
        Random random = new Random();
        if (minColor > 255) {
            minColor = 255;
        }
        if (maxColor > 255) {
            maxColor = 255;
        }
        // 获得r的随机颜色值
        int red = minColor + random.nextInt(maxColor - minColor);
        int green = minColor + random.nextInt(maxColor - minColor);
        int blue = minColor + random.nextInt(maxColor - minColor);
        return new Color(red, green, blue);
    }

}
