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
public class WfProcDesignRestTestCase{


    public String url="http://localhost:8080/iqb.front/";
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
    * 功能：部署流程
    * @author zhaomingli
    * @date 2016-09-01
    * @param procDefId //流程ID
    * @param procDefName //流程名称
    * {@正式代码}
    */
   @Test
   public void deploymentProcess(){
       System.out.println("*********start***********");
       url=url+"WfDesign/deployementProcess";
       map.put("procDefId", "backspace");
       map.put("procDefName", "流程分支");
   }
    
   
   /**
    * 功能：查看已经部署流程列表
    *  {@非正式代码-现在不用-未来可能用到的功能}
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
