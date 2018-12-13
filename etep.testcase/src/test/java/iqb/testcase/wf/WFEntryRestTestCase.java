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

public class WFEntryRestTestCase{

    private String url = "http://localhost:8080/iqb.front/";
    RestTemplate restTemplate = null;
    HttpHeaders headers = null;
    HttpEntity entity = null;
    Map<String, String> map = new HashMap();

    @Before
    public void bef() {
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
    public void insertPropertyMap() {
        url = url + "wfentry/insertProcessProperty";
        map.put("procId", "backspace");
        map.put("procTaskCode", "usertask0");
        map.put("procPropsType", "2");
        map.put("procPropsKey", "QQ4");
        map.put("procPropsValue", "111111");
        map.put("procPropsCreator", "萌萌");
        map.put("procPropsCreatetime", String.valueOf(System.currentTimeMillis()));
        map.put("procPropsUpdator", "萌萌");
        map.put("procPropsUpdatetime", String.valueOf(System.currentTimeMillis()));
        System.out.println("***********2");
    }

    /**
     * 添加节点属性
     */
    @Test
    public void insertIqbWfMyProcMap() {
        url = url + "wfentry/insertIqbWfMyProc";
        map.put("procInstId", "backspace");
        map.put("procUser", "2");
        map.put("procUserType", "url");
        System.out.println("***********map拼接成功");
    }

    /**
     * 通过id删除节点属性
     */
    @Test
    public void deleteByPrimaryKeyTest() {
        System.out.println("*********start***********");
        url = url + "wfentry/deleteByPrimaryKey";
        map.put("id", "13");

    }

    /**
     * 查询属性节点
     */
    @Test
    public void queryFlowProperty() {
        System.out.println("*********start***********");
        url = url + "wfentry/queryFlowProperty";
        map.put("procPropsKey", "url");
        map.put("procPropsValue", "https://www.baidu.com/");
        // map.put("id", "6");
    }

    /**
     * 更新属性节点
     */
    @Test
    public void updateFlowProperty() {
        System.out.println("*********start***********");
        url = url + "wfentry/updateFlowProperty";

        map.put("id", "5");
        map.put("procPropsKey", "url1");
        // map.put("id", "6");
    }

    /**
     * 挂起
     */
    @Test
    public void updateFlowStatus() {
        System.out.println("*********start***********");
        url = url + "wfentry/updateFlowStatus";
        map.put("type", "suspend");
        map.put("procId", "11");
    }

    /**
     * 部署流程
     */
    @Test
    public void deploymentProcess() {
        System.out.println("*********start***********");
        url = url + "wfentry/deployementProcess";
        map.put("bpmnId", "backspace");
        map.put("bpmnName", "爱钱帮流程2");
    }

    /**
     * 启动流程
     */
    @Test
    public void startIns() {
        System.out.println("*********start***********");
        url = url + "wfentry/startInstance";
        map.put("processInstanceByKey", "backspace");
        map.put("userId", "admin");

    }

    /**
     * 启动流程
     */
    @Test
    public void queryTaslOneself() {
        System.out.println("*********start***********");
        url = url + "wfentry/queryTaskOneself";
        map.put("assignee", "one");

    }

    // queryTaskOneself

    /**
     * 业务流程表插入 IQB_WF_PROC
     */
    @Test
    public void insertIqbWfProc() {
        System.out.println("*********start***********");
        url = url + "wfentry/insertIqbWfProc";
        map.put("procInstId", "100");
        map.put("procId", "张三");
        map.put("procName", "张三");
        map.put("procCreator", "1");
        map.put("procBizid", "1");
        map.put("procBiztype", "1");
        map.put("porcMemo", "1");
        map.put("procStatus", "1");

    }

    /**
     * 业务流程任务表
     */
    @Test
    public void updateIqbWfProcSelective() {
        System.out.println("*********start***********");
        url = url + "wfentry/updateIqbWfProcSelective";
        map.put("procInstId", "100");
        map.put("procId", "szgg");
        map.put("procName", "诚招公关");
        map.put("procStatus", "2");
    }

    /**
     * 我的流程插入-依赖业务流程表
     */
    @Test
    public void insertIqbWfMyProc() {
        System.out.println("*********start***********");
        url = url + "wfentry/insertIqbWfMyProc";
        map.put("procInstId", "100");
        map.put("procUser", "张三");
        map.put("procUserType", "1");
    }

    /**
     * 我的流程插入-依赖业务流程表
     */
    @Test
    public void updateIqbWfMyProc() {
        System.out.println("*********start***********");
        url = url + "wfentry/updateIqbWfMyProcSelective";
        map.put("id", "2");
        map.put("procUser", "账务");
    }

    /**
     * 业务流程任务表
     */
    @Test
    public void insertIqbWfProcTask() {
        System.out.println("*********start***********");
        url = url + "wfentry/insertIqbWfProcTask";
        map.put("procInstId", "100");
        map.put("procPtaskcode", "11");
        map.put("procPtaskname", "22");
        map.put("procTaskCommitter", "33");
        map.put("procCtaskcode", "44");
        map.put("procCtaskname", "55");
        map.put("procTaskGroup", "66");
        map.put("procAppointUsers", "77");
        map.put("procLicensor", "88");
        map.put("procMandatary", "99");
        map.put("procTaskAssignee", "10");
        map.put("procTaskApprStatus", "1");
        map.put("procTaskApprOpinion", "12");
        map.put("procTaskStatus", "1");

    }

    /**
     * 业务流程任务表
     */
    @Test
    public void updateIqbWfProcTaskSelective() {
        System.out.println("*********start***********");
        url = url + "wfentry/updateIqbWfProcTaskSelective";
        map.put("id", "1");
        map.put("procTaskCommitter", "诚招公关");
    }

    @After
    public void aft() {
        System.out.println("*********after***********");
        entity = new HttpEntity<Object>(JSON.toJSON(map), headers);
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response =
            restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        LinkedHashMap<?, ?> responseMap = (LinkedHashMap<?, ?>) response.getBody();
        System.out.print("*******************" + responseMap);
        assertTrue(true);
    }

}
