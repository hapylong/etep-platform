/*
 * 软件著作权：北京爱钱帮财富科技有限公司
 * 项目名称：
 *
 * NAME : DesignTestCase.java
 *
 * PURPOSE : 
 *
 * AUTHOR : zhaomingli
 *
 *
 * 创建日期: 2016年8月15日
 * HISTORY：
 * 变更日期 
 */
package iqb.testcase.wf;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;


/**
 * @author zhaomingli
 *
 */
public class DesignTestCase{


    public String url="http://localhost:8080/etep.front/";
    RestTemplate restTemplate=null;
    HttpHeaders headers=null;
    HttpEntity entity=null;
    Map<String, String> map  = new HashMap();
    
    @Before
    public void bef(){
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Charset", "UTF-8");
        System.out.println("**********before***********");
    }
    
    /**
     * 添加节点属性
     */
    @Test
    public void insertPropertyMap(){
          url=url+"wfentry/insertProcessProperty";
          map.put("procId", "backspace");
          map.put("procTaskCode", "usertask0");
          map.put("procPropsType", "2");
          map.put("procPropsKey", "QQ5");
          map.put("procPropsValue", "111111");
          map.put("procPropsCreator", "老王");
          map.put("procPropsCreatetime", String.valueOf(System.currentTimeMillis()));
          map.put("procPropsUpdator", "老王");
          map.put("procPropsUpdatetime", String.valueOf(System.currentTimeMillis()));
          System.out.println("***********2");
    }
 
    /**
     * 通过id删除节点属性
     */
    @Test
    public void deleteByPrimaryKeyTest(){
        System.out.println("*********start***********");
        url=url+"wfentry/deleteByPrimaryKey";
        map.put("id", "18");
        
    }
    
    
    
    /**
     * 查询属性节点
     */
    @Test
    public void queryFlowProperty(){
        System.out.println("*********start***********");
        url=url+"wfentry/queryFlowProperty";
        map.put("procPropsKey", "url");
        /*map.put("procPropsValue", "https://www.baidu.com/");*/
//        map.put("id", "6");
    }
    /**
     * 更新属性节点
     */
    @Test
    public void updateFlowProperty(){
        System.out.println("*********start***********");
        url=url+"wfentry/updateFlowProperty";
        
        map.put("id", "18");
        map.put("procPropsKey", "WW");
//        map.put("id", "6");
    }
    
    
    
    /**
     * 部署流程
     */
    @Test
    public void deploymentProcess(){
        System.out.println("*********start***********");
        url=url+"WfDesign/deployementProcess";
        map.put("procDefId", "borrowOne");
        map.put("procDefName", "第一个车流程001");
    }
    
    /**
     * 部署流程
     */
    @Test
    public void deploymentProcess01(){
        System.out.println("*********start***********");
        url=url+"WfDesign/deployementProcess";
        map.put("procDefId", "source");
        map.put("procDefName", "source分支测试");
    }
    
    /**
     * 功能：查看已经部署流程列表
     */
    @Test
    public void queryDeploymentProcessList(){
        url=url+"wfentry/queryDeplPro";
    }
  
    
    @After
    public void aft(){
        System.out.println("*********after***********");
        entity = new HttpEntity<Object>(JSON.toJSON(map),headers);
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.exchange(url,HttpMethod.POST, entity, Map.class);
        LinkedHashMap<?, ?> responseMap = (LinkedHashMap<?, ?>)response.getBody();
        System.out.print("*******************" + responseMap);
        assertTrue(true);
    }
}
