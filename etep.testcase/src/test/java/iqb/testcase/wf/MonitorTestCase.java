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
public class MonitorTestCase{

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
  //  backspace:1:4
    
    /**
     * 挂起/激活--suspend
     */
    @Test
    public void updateFlowStatus(){
        System.out.println("*********start***********");
        url=url+"wfentry/updateFlowStatus";
//        map.put("type", "flowsuspend");//流程挂起
//        map.put("type", "flowactive");//激活
        
//        map.put("type", "instsuspend");//实例挂起
//          map.put("type", "instactive");//实例激活
//         map.put("type", "del");//实例删除
         map.put("type", "stop");//实例终止
          
//        map.put("procId", "helloworld:1:7504");
          map.put("procId", "10001");//
    }
    
    /**
     * 查看所有流程
     */
    @Test
    public void queryflow(){
        System.out.println("*********start***********");
        url=url+"wfentry/process-list";
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
