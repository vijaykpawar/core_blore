package com.core.blore.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.blore.bean.OTPLog;
import com.blore.bean.User;

/**
 * @athor vijay pawar
 */
public class UserDAO extends GenericJpaRepository<User, Long> {

	public UserDAO(EntityManager eManager) {
		super(eManager);
	}

	public List<String> getMatchedBloreContacts(List<String> contactsTomatch) {
		List<String> contactsMachedfromDB = null;
		String qr = "select num from (select concat(c.cdialCode,u.contactNo) num from user  u,country "
				+ "c where u.idCountry=c.idCountry   union select concat('00',u.contactNo) num from user  u,country "
				+ "c where u.idCountry=c.idCountry ) a "
				+ ""
				+ "where ";
		try {
			StringBuilder subqr = new StringBuilder();
			int length = contactsTomatch.size(), i = 0;
			for (String str : contactsTomatch) {
				str=str.replace(" ", "");
				str=str.replace("+", "");
				++i;
				if (i == length)
					subqr.append("num like '%" + str + "%'");
				else
					subqr.append("num like '%" + str + "%' OR ");
			}
			qr = qr + subqr.toString();
			System.out.println("final quesry is " + qr);
			contactsMachedfromDB = getEntityManager().createNativeQuery(qr)
					.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contactsMachedfromDB;
	}
	
	public List<String> getBloreContacts(List<String> contactsTomatch) {
		List<String> contactsMachedfromDB = null;
		String qr = "select distinct contact from (select concat(c.cdialCode,u.contactNo) num,u.contactNo contact from user  u,country "
				+ "c where u.idCountry=c.idCountry   union select concat('00',u.contactNo) num,u.contactNo contact from user  u,country "
				+ "c where u.idCountry=c.idCountry ) a "
				+ ""
				+ "where ";
		try {
			StringBuilder subqr = new StringBuilder();
			int length = contactsTomatch.size(), i = 0;
			for (String str : contactsTomatch) {
				str=str.replace(" ", "");
				str=str.replace("+", "");
				++i;
				if (i == length)
					subqr.append("num like '%" + str + "%'");
				else
					subqr.append("num like '%" + str + "%' OR ");
			}
			qr = qr + subqr.toString();
			System.out.println("final quesry is " + qr);
			contactsMachedfromDB = getEntityManager().createNativeQuery(qr)
					.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contactsMachedfromDB;
	}

	
	
	

	public User getActiveUserByMobileNum(String mobile) {
		User u = null;
		try {
			u = (User) getEntityManager()
					.createQuery(" from User where contactNo=?")
					.setParameter(1, mobile).getSingleResult();
		} catch (Exception e) {
			u = null;
			e.printStackTrace();
		}
		return u;
	}
	
	public List<User> getUsersByContactList(List<String> clientContactList){
		List<User>  usersDb=null;
		List<String> dbContactsList=getBloreContacts(clientContactList);
		try {
			if(dbContactsList!=null){
				StringBuilder subqr = new StringBuilder();
				int length = dbContactsList.size(), i = 0;
				if(length>0){
					String qr = " from User where contactNo in (";
					for (String str : dbContactsList) {
						++i;
						if (i == length)
							subqr.append("" + str + ")");
						else
							subqr.append("" + str + ",");
					}
					qr = qr + subqr.toString();
					System.out.println("final quesry is " + qr);
					 usersDb=getEntityManager().createQuery(qr)
						.getResultList();			
				}
				
			}
		} catch (Exception e) {
			usersDb=null;
			e.printStackTrace();
		}
		return usersDb;
	}

	public User getActiveUserBySessionToken(String sessionToken) {
		User u = null;
		try {
			u = (User) getEntityManager()
					.createQuery(" from User where sessionToken=?")
					.setParameter(1, sessionToken).getSingleResult();
		} catch (Exception e) {
			u = null;
			e.printStackTrace();
		}
		return u;

	}

}
