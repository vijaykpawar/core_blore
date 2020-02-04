package com.core.blore.bo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.jboss.resteasy.logging.Logger;

import com.core.blore.dal.EntityManagerFactoryBuilt;

/**
 * Base class for Business objects. 
 * 
 * <p>
 * This class provides utility methods for BOs. 
 * You should extends this class in your BO classes. 
 * </p>
 * 
 * @author vijay pawar
 */
public class BaseBO {

	private final transient Logger log = Logger.getLogger(BaseBO.class);

	/** The session. */
	private Map<String, Object> session;

	/** The entity manager. */
	private EntityManager entityManager;

	/**
	 * Instantiates a new base bo.
	 */
	public BaseBO() {
		//session = null;
		entityManager = null;
	}

	/**
	 * Instantiates a new base bo.
	 * 
	 * @param lSession
	 *            the session
	 */
	public BaseBO(final Map<String, Object> lSession) {
		this.session = lSession;
		this.entityManager = null;
	}

	/**
	 * Instantiates a new base bo.
	 * 
	 * @param entityManager
	 *            the entity manager
	 */
	public BaseBO(final EntityManager entityManager) {
		session = new HashMap<String, Object>();
		//session.put(Constants.ENTITY_MANAGER, entityManager);
		this.entityManager = null;
	}

	/**
	 * Gets the entity manager.
	 *
	 * Will instantiate new entity manager if none provided in session.
	 * 
	 * @return the entity manager
	 */
	public final EntityManager getEntityManager() {
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = EntityManagerFactoryBuilt.getEntityManager();
		} 
		return entityManager ;
	}
	
	public final void closeEntityManager() {
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close() ;
			entityManager=null;
		}
	}
	
	/*
	public final EntityManager getEntityManager() {
		if (entityManager == null || !entityManager.isOpen()) {
			if (session != null) {
				if (session.get(Constants.ENTITY_MANAGER) != null) {
					entityManager = (EntityManager) session.get(Constants.ENTITY_MANAGER);
					if (entityManager == null || !entityManager.isOpen()) {
						entityManager = EntityManagerFactoryBuilt.getEntityManager();
						session.put(Constants.ENTITY_MANAGER, entityManager);
					}
				} else {
					entityManager = EntityManagerFactoryBuilt.getEntityManager();
					session.put(Constants.ENTITY_MANAGER, entityManager);
				}
			} else {
				entityManager = EntityManagerFactoryBuilt.getEntityManager();
			}
		}
		return entityManager;
	}
	 */
	/**
	 * Gets the logged in user from session.
	 * 
	 * @return the logged in user
	 */
	
	
	
	/*
	protected void finalize() throws Throwable {
		log.debug("In Finalize"); 

		if (entityManager != null) {
			log.debug("Releasing Entity Manager");
			//entityManager.getTransaction().commit();
			entityManager.close(); 
		}
	}*/
	public static void main(String[] args) {
		EntityManagerFactoryBuilt.getEntityManager();
	}
}
