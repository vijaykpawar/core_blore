package com.blore.bean;

public class CoreResponse {
	private int msgToClient=1500;
	private Object data;

	public int getMsgToClient() {
		return msgToClient;
	}

	public void setMsgToClient(int msgToClient) {
		this.msgToClient = msgToClient;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
