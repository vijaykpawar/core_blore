package com.core.blore.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.blore.bean.Event;
import com.blore.bean.User;

public class EventDAO extends GenericJpaRepository<Event, Long>{

	public EventDAO(EntityManager eManager) {
		super(eManager);
	}
	public List<Event> getEventListByEventIds(List<Long> eventIds){	
		List<Event> events=null;
		try {
			events=getEntityManager().createQuery(" from Event  where eventId  in (?)").setParameter(1,eventIds).getResultList();
		} catch (Exception e) {
			events=null;
			e.printStackTrace();
		}
		return events;
	}
	public List<Long> getEventIdsByUserId(Long userId) {
		List<Long> ids=null;
		try {
			ids=getEntityManager().createNativeQuery(" select event_eventId from event_user  where invities_userId =? ").setParameter(1, userId).getResultList();
		} catch (Exception e) {
		ids=null;
			e.printStackTrace();
		}
		
		return ids;
	}
	
}
