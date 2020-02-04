package com.blore.bean;

import java.util.HashMap;

import javax.ws.rs.core.Response;

import com.core.blore.bo.AuthHelper;

public class CoreAuth {

	public boolean isValid(String sessionToken) {
		return new AuthHelper().isValidClientReq(sessionToken);
	}

	public Response notAuhorised(CoreResponse coreResponse) {
		coreResponse.setMsgToClient(1704);
		coreResponse.setData("You are not athorised");
		return Response.ok(coreResponse).build();
	}
}
