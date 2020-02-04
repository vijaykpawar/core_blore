package com.blore.bean;

public class KeyVal {
	private String key;
	private Object val;

	public KeyVal(String string, String string2) {
		this.key = string;
		this.val = string2;
	}
	public KeyVal(){
		
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	

	public Object getVal() {
		return val;
	}
	public void setVal(Object val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return "KeyVal [key=" + key + ", val=" + val + "]";
	}

}
