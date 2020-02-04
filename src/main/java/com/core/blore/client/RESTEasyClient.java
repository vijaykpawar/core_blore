package com.core.blore.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import antlr.debug.Event;

import com.blore.bean.CoreRequest;
import com.blore.bean.EventReq;
import com.blore.bean.KeyVal;
import com.google.gson.Gson;

/**
 * @athor vijay pawar
 */
public class RESTEasyClient {

	public static void main(String[] args) {
		
		RESTEasyClient restEasyClient=new RESTEasyClient();
		restEasyClient.testSyncContacts();
	
	}

	
	
	
	
	public void testGetCountryList(){
		CoreRequest st = new CoreRequest();
		List<KeyVal> myMap = new ArrayList<KeyVal>();
		myMap.add(new KeyVal("param1", "param1"));
		myMap.add(new KeyVal("param2", "param2"));
		st.setParams(myMap);
		testPostData(st, "/service/getCountryList");	
	}
	private void  testPostData(CoreRequest coreRequest,String serviceName){
		try {
			String url="http://localhost:8080/blore/core"+serviceName;
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client
					.target(url);
			Response response = target.request().post(Entity.entity(coreRequest, "application/json"));
			System.out.println("Posting  request ::");
			System.out.println(new Gson().toJson(coreRequest));
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			System.out.println("Server response ::");
			System.out.println(response.readEntity(String.class));
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void testGenerateOtp(){
			CoreRequest coreRequest=new CoreRequest();
			coreRequest.setClientType("android");
			List<KeyVal> params =new ArrayList<KeyVal>();
			params.add(new KeyVal("mobileNo","9960121738"));
			params.add(new KeyVal("displayName","pankaj"));
			params.add(new KeyVal("countryCode","IN"));
			testPostData(coreRequest, "/service/generateOTP");
			
	}
	public void testCreateEvent(){
		CoreRequest coreRequest=new CoreRequest();
		coreRequest.setClientType("android");
		List<KeyVal> params =new ArrayList<KeyVal>(); 
		KeyVal keyVal=new KeyVal();
		keyVal.setKey("invities");
		List<String> list=new ArrayList<String>();
		list.add("9960121738");
	 
		
		
		
		
		testPostData(coreRequest, "/service/generateOTP");
		
}
	
	public void testSyncContacts(){
		CoreRequest coreRequest=new CoreRequest();
		coreRequest.setClientType("iOs");
		List<KeyVal> params =new ArrayList<KeyVal>();
		KeyVal keyVal=new KeyVal();
		keyVal.setKey("contacts");
		List<String> a=new ArrayList<String>();
		a.add("9960121738");
		a.add("9999999999");
		keyVal.setVal(a);
		params.add(keyVal);
		coreRequest.setParams(params);
		
		testPostData(coreRequest, "/service/syncContacts");
		
}

	
	
	public void testRegister(){
		CoreRequest coreRequest=new CoreRequest();
		coreRequest.setClientType("android");
		List<KeyVal> params =new ArrayList<KeyVal>();
		params.add(new KeyVal("mobileNo","9960121738"));
		params.add(new KeyVal("otp","1234otp"));
		testPostData(coreRequest, "/service/registerUser");
		
}

	
}
