package com.core.blore.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.jboss.resteasy.logging.Logger;
/**
 * EntityManagerFactory Built from persistance.xml.
 * 
 * <p>
 * Use this class to get pre-built EntityManager. 
 * Configuration is done through META-INF/persistance.xml.
 * </p>
 * 
 * @author vijay pawar
 */
public final class EntityManagerFactoryBuilt {

	/** The entity manager factory. */
	private static EntityManagerFactory entityManagerFactory;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(EntityManagerFactoryBuilt.class);

	static {
		rebuildEntityManagerFactory();
	}

	/**
	 * Instantiates a new entity manager factory holder.
	 */
	private EntityManagerFactoryBuilt() {
	}

	/**
	 * Rebuild EntityManagerFactory.
	 */
	public static void rebuildEntityManagerFactory() {
		try {
			String persistanceUnitName = "blore";
			entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnitName);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error Creating EntityManager", e);
		}
	}
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("blore");
	}

	/**
	 * Gets the entity manager.
	 * 
	 * @return the entity manager
	 */
	public static synchronized EntityManager getEntityManager() {
		EntityManager entityManager = null;
		try {
			if (entityManagerFactory == null) {
				rebuildEntityManagerFactory();
			}
			entityManager = entityManagerFactory.createEntityManager();
		} catch (Exception e) {
			LOG.error("getEntityManager", e);
			entityManager = null;
		}
		return entityManager;
	}

	/**
	 * Close the single entityManager instance.
	 * 
	 * @param entityManager
	 *            the entity manager
	 */
	public static void closeEntityManager(final EntityManager entityManager) {
		if (entityManager != null) {
			synchronized (entityManager) {
				if (entityManager.isOpen()) {
					entityManager.clear();
					entityManager.close();
				}
			}
		}
	}

	/**
	 * return session factory.
	 * 
	 * @return the entity manager factory
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

}