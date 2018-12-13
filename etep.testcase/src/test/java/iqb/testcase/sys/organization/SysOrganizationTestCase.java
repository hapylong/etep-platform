package iqb.testcase.sys.organization;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;


public class SysOrganizationTestCase{
    
    private String uri = "http://localhost/iqb.front";
        
    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void insertSysOrganizationInfo() {
        uri = uri + "/sysOrganizationRest/insertSysOrganizationInfo";
    
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Charset", "UTF-8");
        
        Map map  = new HashMap();
        map.put("orgCode", "1001");
        map.put("orgName", "测试用户1");
        
        HttpEntity entity = new HttpEntity(JSON.toJSON(map),headers);
        ResponseEntity response = restTemplate.exchange(uri,HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
        System.out.print("*******************" + responseMap);
        assertTrue(true);
    }
    
    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSysOrganizationInfo() {
        uri = uri + "/sysOrganizationRest/getSysOrganizationInfo";

        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Charset", "UTF-8");
        
        Map map  = new HashMap();
        map.put("pageNo", "1");
        map.put("pageSize", "10");
    
        HttpEntity entity = new HttpEntity(JSON.toJSON(map),headers);
        ResponseEntity response = restTemplate.exchange(uri,HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
        System.out.print("*******************" + responseMap);
        assertTrue(true);
    }

}
