package iqb.testcase.xcredit;

import static org.junit.Assert.assertTrue;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iqb.etep.common.utils.JSONUtil;

public class DbImportTestCase {
	private String baseUrl = "http://118.178.195.195/xcredit_front";
	
	@Test
	public void test() {
//		importData();
//		uploadData();
		queryData();
	}
	
	public void importData() {
//		String url = baseUrl + "/data/dbimport";
	}
	
	public void uploadData() {
		String url = baseUrl + "/data/uploadApi";
		Object data = JSON.toJSON(getUploadData());
		System.out.println(data);
		requestByJson(url, data);
	}
	
	private ArrayList<Map<String, String>> getUploadData() {
		ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();

		Map<String, String> data1 = new HashMap<String, String>();
		data1.put("beginDate", "1448928000000");
		data1.put(
				"bizData",
				"{\"bizHistoryLoanCount\":3,\"bizHistoryFirstLoanDate\":1335801600000,\"bizHistoryLoanTotalAmount\":320000,\"bizOverdueTransactionCount\":1,\"bizOverdueCount\":4,\"bizOverdueMaxAmount\":10000,\"bizOverdueThirtyDaysCount\":2,\"bizOverdueNinetyDaysCount\":1,\"bizOverdueOneEightyDaysCount\":1,\"bizOverdueLastDate\":1438358400000,\"bizCurrentLoanCount\":2,\"bizLastSixMonthsAveragePaybackAmount\":8000,\"currentLoan\":[{\"purpose\":\"装修\",\"beginDate\":1438358400000,\"endDate\":1469980800000,\"totalAmount\":200000,\"balance\":100000},{\"purpose\":\"购车\",\"beginDate\":1448899200000,\"endDate\":1480521600000,\"totalAmount\":100000,\"balance\":50000}]}");
		data1.put("dataType", "9000");
		data1.put("endDate", "1480550400000");
		data1.put("indexDigest",
				"e09d4a9f6d60678c1bc78d151b0fa863ad8866d1a728aa9f2c71cc3ec3540000");
		data1.put("indexType", "1000");
		data1.put("isIndexed", "0");
		data1.put("price", "0");
		data1.put("status", "0");

		Map<String, String> data2 = new HashMap<String, String>();
		data2.put("beginDate", "1448928000000");
		data2.put(
				"bizData",
				"{\"bizHistoryLoanCount\":3,\"bizHistoryFirstLoanDate\":1335801600000,\"bizHistoryLoanTotalAmount\":320000,\"bizOverdueTransactionCount\":1,\"bizOverdueCount\":4,\"bizOverdueMaxAmount\":10000,\"bizOverdueThirtyDaysCount\":2,\"bizOverdueNinetyDaysCount\":1,\"bizOverdueOneEightyDaysCount\":1,\"bizOverdueLastDate\":1438358400000,\"bizCurrentLoanCount\":2,\"bizLastSixMonthsAveragePaybackAmount\":8000,\"currentLoan\":[{\"purpose\":\"装修\",\"beginDate\":1438358400000,\"endDate\":1469980800000,\"totalAmount\":200000,\"balance\":100000},{\"purpose\":\"购车\",\"beginDate\":1448899200000,\"endDate\":1480521600000,\"totalAmount\":100000,\"balance\":50000}]}");
		data2.put("dataType", "9001");
		data2.put("endDate", "1480550400000");
		data2.put("indexDigest",
				"d1ebd3fc7f0f6f84d838e0fb73fd2262fc07126750a129cd7a1faa05d9a00000");
		data2.put("indexType", "1001");
		data2.put("isIndexed", "0");
		data2.put("price", "0");
		data2.put("status", "0");

		Map<String, String> data3 = new HashMap<String, String>();
		data3.put("beginDate", "1448928000000");
		data3.put(
				"bizData",
				"{\"initialInvestmentTime\":1438358400000,\"lastSixMonthsInvestmentAmount\":5000,\"totalInvestmentAmount\":100000}");
		data3.put("dataType", "9002");
		data3.put("endDate", "1480550400000");
		data3.put("indexDigest",
				"1c126681512bf17952abab084a2a79c01afe193da960240f8a0f8361727e0000");
		data3.put("indexType", "1000");
		data3.put("isIndexed", "0");
		data3.put("price", "0");
		data3.put("status", "0");
		
		data.add(data1);
		data.add(data2);
		data.add(data3);
		
		return data;
	}
	
	@SuppressWarnings("rawtypes")
	public void queryData() {
		String url = baseUrl + "/index/getData";
		ArrayList<Map<String, String>> params = getIndexParamList();
		
		for (int i = 0; i < params.size(); i++) {
			String indexType = params.get(i).get("indexType");
			String indexDigest = params.get(i).get("indexDigest");
			String indexData = queryIndexData(indexType, indexDigest);
			System.out.println("get Index:" + indexData);
			JSONObject obj = JSONUtil.strToJSON(indexData);
			JSONArray arrayData = obj.getJSONArray("data");
			
			for (int j = 0; j < arrayData.size(); j++) {
				JSONObject data = arrayData.getJSONObject(j);
				JSONObject detail = data.getJSONObject("data");
				
				MultiValueMap<String, String> requestData = new LinkedMultiValueMap<String, String>();
				requestData.add("indexDigest", indexDigest);
				requestData.add("indexType", indexType);
				requestData.add("dataType", detail.getString("dataType"));
				requestData.add("cert", detail.getString("cert"));
				
				String response = requestByPost(url, requestData);
				System.out.println("** response data:" + response);
			}
		}
	}

	private ArrayList<Map<String, String>> getIndexParamList() {
		ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();

		Map<String, String> data1 = new HashMap<String, String>();
		data1.put("indexDigest", "e09d4a9f6d60678c1bc78d151b0fa863ad8866d1a728aa9f2c71cc3ec3541f79");
		data1.put("indexType", "1000");
		
		Map<String, String> data2 = new HashMap<String, String>();
		data2.put("indexDigest", "d1ebd3fc7f0f6f84d838e0fb73fd2262fc07126750a129cd7a1faa05d9a06f39");
		data2.put("indexType", "1001");
		
		Map<String, String> data3 = new HashMap<String, String>();
		data3.put("indexDigest", "1c126681512bf17952abab084a2a79c01afe193da960240f8a0f8361727e9569");
		data3.put("indexType", "1000");
		
		data.add(data1);
		data.add(data2);
		data.add(data3);
		
		return data;
	}

	public String queryIndexData(String indexType, String indexDigest) {
		String url = baseUrl + "/index/get";
		url = url + "?indexType=" + indexType + "&indexDigest=" + indexDigest;
		
		return requestByGet(url);
	}
	
	@SuppressWarnings("rawtypes")
	private void requestByJson(String url, Object data) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept-Charset", "UTF-8");
		HttpEntity entity = new HttpEntity<Object>(data, headers);
		ResponseEntity<Map> response = restTemplate.exchange(
				url, HttpMethod.POST, entity, Map.class);
		System.out.println("** response data:" + response);
		assertTrue(true);
	}
	
	private String requestByPost(String url, MultiValueMap<String, String> uriVariables) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("Accept-Charset", "UTF-8");

		return restTemplate.postForObject(url, uriVariables, String.class);
	}
	
	private String requestByGet(String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		headers.set("Accept-Charset", "UTF-8");

		return restTemplate.getForObject(url, String.class); 
	}
}
