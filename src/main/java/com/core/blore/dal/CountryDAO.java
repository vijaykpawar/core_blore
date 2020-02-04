package com.core.blore.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.blore.bean.Country;

public class CountryDAO extends GenericJpaRepository<Country, Long> {

	public CountryDAO(EntityManager eManager) {
		super(eManager);
	}

	public List<Country> getAllCountryList() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Country> q = cb.createQuery(Country.class);
		Root<Country> c = q.from(Country.class);
		q.select(c);
		TypedQuery<Country> query = getEntityManager().createQuery(q);
		List<Country> results = query.getResultList();
		return results;
	}

	public Country findCountryCode(String countryCode) {
		Country country = null;
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Country> q = cb.createQuery(Country.class);
			Root<Country> c = q.from(Country.class);
			q.select(c);
			q.where(cb.equal(c.get("ccode"), countryCode));
			TypedQuery<Country> query = getEntityManager().createQuery(q);
			country = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return country;
		
	}

}
