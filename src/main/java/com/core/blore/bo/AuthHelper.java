package com.core.blore.bo;

import com.blore.bean.User;
import com.core.blore.dal.UserDAO;

public class AuthHelper extends BaseBO {

	public boolean isValidClientReq(String sessionToken) {
		try {
			User u = new UserDAO(getEntityManager())
					.getActiveUserBySessionToken(sessionToken);
			if (u != null)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeEntityManager();
		}
		return false;
	}
}
