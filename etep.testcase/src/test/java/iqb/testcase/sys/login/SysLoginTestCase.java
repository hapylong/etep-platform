package iqb.testcase.sys.login;

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


public class SysLoginTestCase{
    
    
    private String uri = "http://localhost/etep.front";
        
    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sysLogin() {
        uri = uri + "/sysLogin/login";
    
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Charset", "UTF-8");
        
        Map map  = new HashMap();
        map.put("userName", "灵石");
        map.put("userPassword", "2222");
        
        HttpEntity entity = new HttpEntity(JSON.toJSON(map),headers);
        ResponseEntity response = restTemplate.exchange(uri,HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
        System.out.print("*******************" + responseMap);
        assertTrue(true);
    }
    
    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void doLogout() {
        uri = uri + "/sysLogin/logout";
        
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Charset", "UTF-8");
        
        Map map  = new HashMap();
        map.put("userName", "灵石");
        map.put("userPassword", "2222");
        
        HttpEntity entity = new HttpEntity(JSON.toJSON(map),headers);
        ResponseEntity response = restTemplate.exchange(uri,HttpMethod.POST, entity, Map.class);
        LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
        System.out.print("*******************" + responseMap);
        assertTrue(true);
    }

}
