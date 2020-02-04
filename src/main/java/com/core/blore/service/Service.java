package com.core.blore.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.blore.bean.CoreAuth;
import com.blore.bean.CoreRequest;
import com.blore.bean.CoreResponse;
import com.blore.bean.Country;
import com.core.blore.bo.ContactsHelper;
import com.core.blore.bo.CountryBO;
import com.core.blore.bo.EventBO;
import com.core.blore.bo.OTPHelperBO;
import com.core.blore.bo.UserBO;


/**
 * @athor vijay pawar
 */

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
public class Service extends CoreAuth {


	@POST
	@Path("/getDialCode")
	@Consumes("application/json")
	public Response getDialCode(CoreRequest coreRequest,
			@Context HttpServletRequest req, @Context HttpServletResponse resp) {

		System.out.println("core request consulmed is ::"
				+ coreRequest.toString());
		String output = coreRequest.toString();

		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/getCountryList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountryList() {
		System.out.println("called getCountryList ***************8");
		CoreResponse response = new CoreResponse();
		List<Country> data = null;
		data = new CountryBO().getAllCountryList();
		if (data != null) {
			response.setMsgToClient(1200);
			response.setData(data);
		}
		return Response.ok(response).build();
	}
	
	
	@POST
	@Path("/getEventList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventList(CoreRequest coreRequest) {
		
		CoreResponse response = new CoreResponse();
	
		if (isValid(coreRequest.getSessionToken())) {
			try {
				EventBO eventBO = new EventBO();
				response=eventBO.getEventList(coreRequest);
			} catch (Exception e) {
				response.setMsgToClient(1405);
				e.printStackTrace();
			}	
			return Response.ok(response).build();
		}else{
			System.out.println("throw an errror  ########################## not valid session token ");
			return notAuhorised(response);
		}
	}

	@POST
	@Path("/registerUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(CoreRequest coreRequest,
			@Context HttpServletRequest req, @Context HttpServletResponse resp) {
		CoreResponse response = new CoreResponse();
		
		try {
			UserBO userBO = new UserBO();
			response = userBO.registerUser(coreRequest, req);
		} catch (Exception e) {
			response.setMsgToClient(1405);
		}
		
		return Response.ok(response).build();
	}

	@POST
	@Path("/generateOTP")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getnerateOTP(CoreRequest coreRequest,
			@Context HttpServletRequest httpServletRequest) {
		
		CoreResponse response = new CoreResponse();
		try {
			OTPHelperBO otpHelperBO = new OTPHelperBO();

			response = otpHelperBO.getOTPforUser(coreRequest);
		} catch (Exception e) {
			response.setMsgToClient(1405);
			e.printStackTrace();
		}
		
		return Response.ok(response).build();
	}

	
	@POST
	@Path("/createEvent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvent(CoreRequest coreRequest,
			@Context HttpServletRequest httpServletRequest) {
		
		CoreResponse response = new CoreResponse();
		if (isValid(coreRequest.getSessionToken())) {
			try {
				EventBO eventBO = new EventBO();
				response=eventBO.createEvent(coreRequest);
			} catch (Exception e) {
				response.setMsgToClient(1405);
				e.printStackTrace();
			}	
		
			return Response.ok(response).build();
		}else{
			System.out.println("throw an errror  ########################## not valid session token ");
			return notAuhorised(response);
		}
	}

	
	
	@POST
	@Path("/syncContacts")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response syncContacts(CoreRequest coreRequest,
			@Context HttpServletRequest httpServletRequest) {
		CoreResponse response = new CoreResponse();
		if (isValid(coreRequest.getSessionToken())) {
			try {
		
				ContactsHelper contactsHelper = new ContactsHelper();
				response = contactsHelper.getBloreContacts(coreRequest);
			} catch (Exception e) {
				response.setMsgToClient(1405);
				e.printStackTrace();
			}
		
			return Response.ok(response).build();
		} else {
			System.out.println("throw an errror  ########################## not valid session token ");
			return notAuhorised(response);
		}
	}	

}
