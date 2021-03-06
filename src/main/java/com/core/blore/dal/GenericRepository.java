package com.core.blore.dal;

import java.io.Serializable;

import java.util.List;
import java.util.Map;


/**
 * The Interface GenericRepository.
 * 
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 *            
 * @author Rohan Jamkhedkar
 */
public interface GenericRepository<T, ID extends Serializable> {

	/**
	 * Get the Class of the entity.
	 * 
	 * @return the class
	 */
	Class<T> getEntityClass();

	/**
	 * Find an entity by its primary key.
	 * 
	 * @param id
	 *            the primary key
	 * @return the entity
	 * @throws Exception
	 *             the exception
	 */
	T findById(final ID id) throws Exception;

	/**
	 * Load all entities.
	 * 
	 * @return the list of entities
	 * @throws Exception
	 *             the exception
	 */
	List<T> findAll() throws Exception;

	/**
	 * Find entities based on an example.
	 * 
	 * @param exampleInstance
	 *            the example
	 * @return the list of entities
	 * @throws Exception
	 *             the exception
	 */
	List<T> findByExample(final T exampleInstance) throws Exception;

	/**
	 * Find using a named query.
	 * 
	 * @param queryName
	 *            the name of the query
	 * @param params
	 *            the query parameters
	 * @return the list of entities
	 * @throws Exception
	 *             the exception
	 */
	List<T> findByNamedQuery(final String queryName, Object... params) throws Exception;

	/**
	 * Find using a named query.
	 * 
	 * @param queryName
	 *            the name of the query
	 * @param params
	 *            the query parameters
	 * @return the list of entities
	 * @throws Exception
	 *             the exception
	 */
	List<T> findByNamedQueryAndNamedParams(final String queryName, final Map<String, ? extends Object> params) throws Exception;

	/**
	 * Count all entities.
	 * 
	 * @return the number of entities
	 * @throws Exception
	 *             the exception
	 */
	int countAll() throws Exception;

	/**
	 * Count entities based on an example.
	 * 
	 * @param exampleInstance
	 *            the search criteria
	 * @return the number of entities
	 * @throws Exception
	 *             the exception
	 */
	int countByExample(final T exampleInstance) throws Exception;

	/**
	 * save an entity. This can be either a INSERT or UPDATE in the database.
	 * 
	 * @param entity
	 *            the entity to save
	 * @return the saved entity
	 * @throws Exception
	 *             the exception
	 */
	T persist(final T entity) throws Exception;

	/**
	 * delete an entity from the database.
	 * 
	 * @param entity
	 *            the entity to delete
	 * @throws Exception
	 *             the exception
	 */
	void delete(final T entity) throws Exception;

	/**
	 * Delete by id.
	 * 
	 * @param entityId
	 *            the entity id
	 * @throws Exception
	 *             the exception
	 */
	void deleteById(ID entityId) throws Exception;

	/**
	 * Update.
	 * 
	 * @param t
	 *            the t
	 * @return the t
	 * @throws Exception
	 *             the exception
	 */
	T update(T t) throws Exception;
}
