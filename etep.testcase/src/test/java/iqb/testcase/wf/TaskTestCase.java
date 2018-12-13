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

import org.apache.ibatis.annotations.Param;
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
public class TaskTestCase{


    public String url="http://localhost:8080/etep.front/";
    RestTemplate restTemplate=null;
    HttpHeaders headers=null;
    HttpEntity entity=null;
    Map<String, String> map  = new HashMap();
    Map map1 = new HashMap();
    
    @Before
    public void bef(){
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Charset", "UTF-8");
        System.out.println("**********before***********");
    }
  
    
    /**
     * 启动流程
     * @param processInstanceByKey 流程id 
     * @param procBizid 业务id 
     * @param procBiztype 业务类型 
     * @param procMemo 流程摘要
     * @param loginUser 登录用户
     * @param assigner 指定处理人
     * openone
     */
    @Test
    public void startIns(){
        System.out.println("*********start***********");
        url=url+"wfentry/startInstance";
        map.put("processInstanceByKey", "borrowOne");
        map.put("procBizid", "1");
        map.put("procBiztype", "0");
        map.put("userId", "Alibaba");
        map.put("assigner", "Alibaba02");
        map.put("procMemo", "资产投资爱钱帮，走向人生巅峰！");
    }
    
    /**
     * 查看个人代办
     */
    @Test
    public void queryTaslOneself(){
        System.out.println("*********start***********");
        url=url+"wfentry/queryTaskOneself";
        map.put("assignee", "admin100");
    }
    
    /**
     * 处理任务
     */
    /**
     * 功能：委托授权任务接口
     * 
     * @param instId
     *            流程实例id
     * @param taskId
     *            任务表id
     * @param userId
     *            当前登录用户
     * @param assigner
     *            受托人
     * @date 2016-08-19
     * @author zhaomingli
     */
    @Test
    public void futureHandle(){
        System.out.println("*********start***********");
        url=url+"wfentry/taskHandle";
        //委托
      /*  map.put("type", "entrust");
        map.put("instId","15001");
        map.put("iwptId", "15");
        map.put("userId", "Ki00");
        map.put("assigner", "admin");*/
        //签收
        map.put("type", "assign");
        map.put("instId","25011");//流程实例
        map.put("assigner", "admin4");//签收人
        map.put("iwptId", "38");//新建流程任务表id  
     
        
        //取消签收
       /* map.put("type", "unassign");
        map.put("instId","7501");//流程实例
        map.put("iwptId", "26");//新建流程任务表id
*/        
        
        //处理任务
       /* map.put("type", "handle");
        map.put("iwptId", "46");//新建流程任务表id  
        map.put("instId","10001");//流程实例
        map.put("userId", "admin100");//当前登录用户
        map.put("assigner", "admin5");//指定下一步处理人
        map.put("taskApprStatus", "1");
        map.put("taskApprOpinion", "完美");*/
        
        //流程撤回
       /* map.put("type", "rollback");
        map.put("taskId", "7508");//撤回到任务id
        map.put("iwptId", "26");//新建流程任务表id  
        map.put("instId","7501");//流程实例
        map.put("userId", "K077");//当前登录用户
        */        
        //流程退回
       /* map.put("type", "rollbacked");
        map.put("instId", "25011");//当前任务id
        map.put("usertask", "usertask1");//回退节点
        map.put("userId", "admin7");//当前登录用户
        map.put("iwptId", "39");//新建流程任务表id  
        map.put("taskApprStatus", "0");//'1：通过\r\n            0：拒绝',
        map.put("taskApprOpinion", "驳回");
        map.put("assigner", "admin10");//指定下一步处理人
*/       
        
        
        //任务重分配
      /*  map.put("type", "setassigner");
        map.put("iwptId", "46");//新建流程任务表id  
        map.put("instId","10001");//流程实例
        map.put("assigner", "admin100");//指定下一步处理人
*/        
    }
    
    
    //queryPersonWaitHandle
    

    /**
     * 查看个人代办
     */
    @Test
    public void queryPersonWaitHandle(){
        System.out.println("*********start***********");
        url=url+"wfentry/queryPersonWaitHandle";
        map.put("userId", "LL55");
        map.put("groupId", "check01");
        map.put("procName", "");
        map.put("procCtaskname", "");
        map.put("procTaskStatus", "");
        map.put("pageNum", "1");
        map.put("pageSize", "10");
        
    }
    /**
     * 查看我的流程
     */
    @Test
    public void queryMyProcWfList(){
        System.out.println("*********start***********");
        url=url+"wfentry/queryMyProcWfList";
        map.put("userId", "Ki44");
        map.put("procName", "爱钱帮");//名称名称
        map.put("procMemo", "");//流程说明
        map.put("procStatus", "1");//流程状态
        map.put("procCreatetimeStart", "1471847440");//流程创建开始时间
        map.put("procCreatetimeEnd", "1471847459");//流程创建结束时间
        map.put("procEndtimeStart", "");//流程完成开始日期
        map.put("procEndtimeEnd", "");//流程完成结束日期
        //'Ki44'
    }
    
    
    
    
    
    /**
     * 测试
     */
   /* @Test
    public void test(){
        System.out.println("*********start***********");
        url=url+"wfentry/taskHandletest";
        map1.put("id", new String[]{"5","6"});
    }
    */
    
  
    
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
