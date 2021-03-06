package com.core.blore.dal;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;


/**
 * The Class GenericJpaRepository.
 * 
 * This class is a repository containing all common database operations.
 * 
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 *            
 * @author Rohan Jamkhedkar
 */
public class GenericJpaRepository<T, ID extends Serializable> implements GenericRepository<T, ID> {

	/** The persistent class. */
	private final Class<T> persistentClass;

	/** The entity manager. */
	private EntityManager entityManager;

	/**
	 * Instantiates a new generic jpa repository.
	 * 
	 * @param eManager
	 *            the entity manager
	 */
	@SuppressWarnings("unchecked")
	public GenericJpaRepository(final EntityManager eManager) {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.entityManager = eManager;
	}

	/**
	 * Instantiates a new generic jpa repository.
	 * 
	 * @param pClass
	 *            the persistent class
	 * @param eManager
	 *            the entity manager
	 */
	public GenericJpaRepository(final Class<T> pClass, final EntityManager eManager) {
		super();
		this.persistentClass = pClass;
		this.entityManager = eManager;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @return the entity class
	 *
	 */
	public final Class<T> getEntityClass() {
		return persistentClass;
	}

	/**
	 * Gets the entity manager.
	 * 
	 * @return the entity manager
	 */
	public final EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @return the int
	 * @throws Exception
	 *             the exception
	 *
	 */
	public int countAll() throws Exception {
		return countByCriteria();
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param exampleInstance
	 *            the example instance
	 * @return the int
	 * @throws Exception
	 *             the exception
	 *  
	 */
	public int countByExample(final T exampleInstance) throws Exception {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());
		crit.add(Example.create(exampleInstance));

		return (Integer) crit.list().get(0);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @return the list
	 * @throws Exception
	 *             the exception
	 *
	 */
	public List<T> findAll() throws Exception {
		return findByCriteria();
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param exampleInstance
	 *            the example instance
	 * @return the list
	 * @throws Exception
	 *             the exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByExample(final T exampleInstance) throws Exception {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		final List<T> result = crit.list();
		return result;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 * @throws Exception
	 *             the exception
	 * 
	 */
	public T findById(final ID id) throws Exception {
		final T result = getEntityManager().find(persistentClass, id);
		return result;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param name
	 *            the name
	 * @param params
	 *            the params
	 * @return the list
	 * @throws Exception
	 *             the exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(final String name, final Object... params) throws Exception {
		javax.persistence.Query query = getEntityManager().createNamedQuery(name);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		final List<T> result = (List<T>) query.getResultList();
		return result;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param name
	 *            the name
	 * @param params
	 *            the params
	 * @return the list
	 * @throws Exception
	 *             the exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndNamedParams(final String name, final Map<String, ? extends Object> params) throws Exception {
		javax.persistence.Query query = getEntityManager().createNamedQuery(name);

		for (final Map.Entry<String, ? extends Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final List<T> result = (List<T>) query.getResultList();
		return result;
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 * 
	 * @param criterion
	 *            the criterion
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	protected List<T> findByCriteria(final Criterion... criterion) throws Exception {
		return findByCriteria(-1, -1, criterion);
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 * 
	 * @param firstResult
	 *            the first result
	 * @param maxResults
	 *            the max results
	 * @param criterion
	 *            the criterion
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final int firstResult, final int maxResults, final Criterion... criterion) throws Exception {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		final List<T> result = crit.list();
		return result;
	}

	/**
	 * Count by criteria.
	 * 
	 * @param criterion
	 *            the criterion
	 * @return the int
	 * @throws Exception
	 *             the exception
	 */
	protected int countByCriteria(final Criterion... criterion) throws Exception {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		return (Integer) crit.list().get(0);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param entity
	 *            the entity
	 * @throws Exception
	 *             the exception
	 * 
	 */
	public void delete(final T entity) throws Exception {
		getEntityManager().remove(entity);
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param entityId
	 *            the entity id
	 * @throws Exception
	 *             the exception
	 * 
	 */
	public void deleteById(final ID entityId) throws Exception {
		T t;
		try {
			t = findById(entityId);
			if (t != null) {
				delete(t);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param entity
	 *            the entity
	 * @return the t
	 * @throws Exception
	 *             the exception
	 *
	 */
	public T persist(final T entity) throws Exception {
		getEntityManager().persist(entity);
		return entity;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param t
	 *            the t
	 * @return the t
	 * @throws Exception
	 *             the exception
	 * 
	 */
	public T update(final T t) throws Exception {

		T mergedEntity = null;
		mergedEntity = getEntityManager().merge(t);
		return mergedEntity;
	}

}
