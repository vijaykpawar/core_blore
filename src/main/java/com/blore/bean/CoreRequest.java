package com.blore.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

public class CoreRequest {

	private String sessionToken;
	private String clientType;
	private String osVersion;
	@JsonIgnore
	private String name;
	
	private List<KeyVal> params = new ArrayList<KeyVal>();

	@JsonIgnore
	private  HashMap<String,Object> map = new HashMap<String, Object>();

	
	public List<KeyVal> getParams() {
		return params;
	}

	public void setParams(List<KeyVal> params) {
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		System.out.println("here this request is called by client ::"+clientType);
		this.clientType = clientType;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	@Override
	public String toString() {
		return "CoreRequest [params=" + params + "]";
	}

	public Object getValue(String key) {
		System.out.println("prams .lenghth is ::"+params.size());
		if (map.get(key) == null) {
			for (KeyVal k : params) {
				map.put(k.getKey(), k.getVal());
			}
		}
		System.out.println("key is::"+key+"     vcalue is ::" +map.get(key));
		return map.get(key);
	}

}
