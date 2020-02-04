package com.core.blore.dal;

import java.util.List;

import javax.persistence.EntityManager;

import com.blore.bean.Event;
import com.blore.bean.User;
import com.blore.bean.UserEvents;

public class UserEventsDAO extends GenericJpaRepository<UserEvents, Long> {

	public UserEventsDAO(EntityManager eManager) {
		super(eManager);
		// TODO Auto-generated constructor stub
	}

	public List<Event> getEventListFromUSerId(User userId) {
		List<Event> events = null;
		try {
			events = getEntityManager()
					.createQuery(
							" select event from UserEvents u where u.user=:ur ").setParameter("ur",userId )
					.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}

}
