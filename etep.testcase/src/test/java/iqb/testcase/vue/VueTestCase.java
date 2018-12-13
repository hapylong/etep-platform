package iqb.testcase.vue;

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

public class VueTestCase {
	private String uri = "http://localhost/iqb.front";
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getData() {
		uri = uri + "/VueTest/getData";

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
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void insertData() {
		uri = uri + "/VueTest/insertData";

		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept-Charset", "UTF-8");
		
		Map map  = new HashMap();
		map.put("userCode", "1001");
		map.put("userName", "测试用户1");
		map.put("age", "10");
		map.put("phone", "13912345678");
		map.put("email", "13912345678@139.com");
		
		HttpEntity entity = new HttpEntity(JSON.toJSON(map),headers);
		ResponseEntity response = restTemplate.exchange(uri,HttpMethod.POST, entity, Map.class);
		LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
		System.out.print("*******************" + responseMap);
		assertTrue(true);
	}

	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateDate() {
		uri = uri + "/VueTest/updateData";

		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept-Charset", "UTF-8");
		
		Map map  = new HashMap();
		map.put("userCode", "1002");
		map.put("userName", "测试用户1001");
		map.put("age", "15");
		map.put("phone", "13912345678");
		map.put("email", "13912345678@139.com");
		
		HttpEntity entity = new HttpEntity(JSON.toJSON(map),headers);
		ResponseEntity response = restTemplate.exchange(uri,HttpMethod.POST, entity, Map.class);
		LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
		System.out.print("*******************" + responseMap);
		assertTrue(true);
	}
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteData() {
		uri = uri + "/VueTest/deleteData";

		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept-Charset", "UTF-8");
		
		Map map  = new HashMap();
		map.put("userCode", "1");
		
		HttpEntity entity = new HttpEntity(JSON.toJSON(map),headers);
		ResponseEntity response = restTemplate.exchange(uri,HttpMethod.POST, entity, Map.class);
		LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
		System.out.print("*******************" + responseMap);
		assertTrue(true);
	}
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testRedis() {
		uri = uri + "/VueTest/testRedis";
		
		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept-Charset", "UTF-8");
		
		Map map  = new HashMap();
		map.put("key", "1001");
		map.put("value", "1002");
		
		HttpEntity entity = new HttpEntity(JSON.toJSON(map),headers);
		ResponseEntity response = restTemplate.exchange(uri,HttpMethod.POST, entity, Map.class);
		LinkedHashMap responseMap = (LinkedHashMap)response.getBody();
		System.out.print("*******************" + responseMap);
		assertTrue(true);
	}
}
