package com.core.blore.bo;

import java.util.List;

import com.blore.bean.Country;
import com.core.blore.dal.CountryDAO;

public class CountryBO extends BaseBO {

	public List<Country> getAllCountryList() {

		List<Country> allCountryList = null;
		try {
			allCountryList = new CountryDAO(getEntityManager())
					.getAllCountryList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		return allCountryList;
	}
}
