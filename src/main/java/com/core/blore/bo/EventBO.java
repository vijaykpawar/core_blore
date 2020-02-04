package com.core.blore.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blore.bean.CoreRequest;
import com.blore.bean.CoreResponse;
import com.blore.bean.Event;
import com.blore.bean.User;
import com.blore.bean.UserEvents;
import com.core.blore.dal.EventDAO;
import com.core.blore.dal.UserDAO;
import com.core.blore.dal.UserEventsDAO;

public class EventBO extends BaseBO {

	public CoreResponse createEvent(CoreRequest coreRequest) {
		CoreResponse coreResponse = new CoreResponse();
		UserDAO userDAO = new UserDAO(getEntityManager());
		EventDAO eventDAO=new EventDAO(getEntityManager());
		UserEventsDAO userEventsDAO=new UserEventsDAO(getEntityManager());
		List<String> clientContacts = (List<String>) coreRequest
				.getValue("invities");
		List<User> invities = userDAO.getUsersByContactList(clientContacts);
		try {
			if (invities != null) {
				User userCreatedBy = userDAO
						.getActiveUserBySessionToken(coreRequest
								.getSessionToken());
				User user_Me=userDAO.getActiveUserBySessionToken(coreRequest.getSessionToken());
				
				invities.add(user_Me);
				// create an new Event
				getEntityManager().getTransaction().begin();
				Event event = new Event();
				if (userCreatedBy != null)
					event.setCreatedBy(userCreatedBy.getUserId());
				event.setEventName((String) coreRequest.getValue("eventName"));
				event.setDuaration((Integer) coreRequest.getValue("duration"));
				event.setEventType((Integer) coreRequest.getValue("eventType"));
				event.setLatitude((Double) coreRequest.getValue("latitude"));
				event.setLongitude((Double) coreRequest.getValue("longitude"));
				event.setCreatedDate(new Date(System.currentTimeMillis()));
				event=eventDAO.persist(event);
				for(User user:invities){
					UserEvents userEvents=new UserEvents();
					if(user.getSessionToken().equals(coreRequest.getSessionToken()))
							userEvents.setStatus("Created By Me");
						else
							userEvents.setStatus("INVITED");
					userEvents.setEvent(event);
					userEvents.setUser(user);
					userEventsDAO.persist(userEvents);
				}
				getEntityManager().getTransaction().commit();
				coreResponse.setMsgToClient(1200);
				coreResponse.setData("Event creted successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		}finally{
			closeEntityManager();
		}
		return coreResponse;
	}

	public CoreResponse getEventList(CoreRequest coreRequest) {
		CoreResponse coreResponse = new CoreResponse();
		UserDAO userDAO = new UserDAO(getEntityManager());
		try {
			User userDb=userDAO.getActiveUserBySessionToken(coreRequest.getSessionToken());
			UserEventsDAO userEventsDAO=new UserEventsDAO(getEntityManager());
			List<Event> userDbEvents=userEventsDAO.getEventListFromUSerId(userDb);
			if(userDbEvents==null)
				userDbEvents=new ArrayList<Event>();
			coreResponse.setData(userDbEvents);
			coreResponse.setMsgToClient(1200);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeEntityManager();
		}
		return coreResponse;
	}
}
