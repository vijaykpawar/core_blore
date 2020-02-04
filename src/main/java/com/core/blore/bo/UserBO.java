package com.core.blore.bo;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.blore.bean.CoreRequest;
import com.blore.bean.CoreResponse;
import com.blore.bean.Country;
import com.blore.bean.OTPLog;
import com.blore.bean.User;
import com.core.blore.bo.OTPHelperBO.OTPRes;
import com.core.blore.crypto.EncryptionUtil;
import com.core.blore.dal.CountryDAO;
import com.core.blore.dal.OTPLogDAO;
import com.core.blore.dal.UserDAO;
import com.core.blore.utils.CoreUtils;

public class UserBO extends BaseBO {

	public class RegisterSucess implements Serializable {
		public String sessionToken;
		public String msg;
	}

	public CoreResponse registerUser(CoreRequest coreRequest,
			HttpServletRequest req) {
		CoreResponse coreResponse = new CoreResponse();
		RegisterSucess rs = new RegisterSucess();
		try {
			// validate is valid otp or not
			String mobileNum = (String) coreRequest.getValue("mobileNo");
			String otp = (String) coreRequest.getValue("otp");
			OTPLogDAO otpLogDAO = new OTPLogDAO(getEntityManager());
			OTPLog dbOtpLog = otpLogDAO.getByMobileandOTP(mobileNum, otp);
			if (dbOtpLog != null) {
				if (dbOtpLog.getUser() != null
						&& dbOtpLog.getUser().getIsActive()) {
					coreResponse.setMsgToClient(1400);
				}
				getEntityManager().getTransaction().begin();
				// createSession token
				String token = getSessionToken(mobileNum);
				dbOtpLog.getUser().setIsActive(true);
				dbOtpLog.getUser().setSessionToken(token);
				otpLogDAO.persist(dbOtpLog);
				getEntityManager().getTransaction().commit();

				rs.sessionToken = token;
				rs.msg = CoreUtils.REG_SUCCESS;
				coreResponse.setData(rs);
				coreResponse.setMsgToClient(1200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
		return coreResponse;
	}

	private String createSessionToken() {
		String chars = "0123456789";
		final int PW_LENGTH = 6;
		Random rnd = new SecureRandom();
		StringBuilder pass = new StringBuilder();
		for (int i = 0; i < PW_LENGTH; i++)
			pass.append(chars.charAt(rnd.nextInt(chars.length())));
		return pass.toString();
	}

	public User getUserForRegistration(CoreRequest coreRequest) {
		User user = null;
		try {
			String mobile = (String) coreRequest.getValue("mobileNo");
			UserDAO userDAO = new UserDAO(getEntityManager());
			user = userDAO.getActiveUserByMobileNum(mobile);
			if (user == null)
				user = new User();
			getEntityManager().getTransaction().begin();
			user.setContactNo(mobile);
			user.setDisplayName((String) coreRequest.getValue("displayName"));
			user.setCreatedDate(new Date(System.currentTimeMillis()));
			user.setIsActive(false);
			user.setIsLocked(false);
			String countryCode = (String) coreRequest.getValue("countryCode");
			System.out
					.println("Country recived in requset is ::" + countryCode);
			CountryDAO countryDAO = new CountryDAO(getEntityManager());
			Country countryDb = countryDAO.findCountryCode(countryCode);
			System.out.println("Country : " + countryDb);
			user.setCountry(countryDb);
			user = userDAO.persist(user);
			getEntityManager().getTransaction().commit();
			System.out.println("User : " + user);
		} catch (Exception e) {
			e.printStackTrace();
			getEntityManager().getTransaction().rollback();
		} finally {
			closeEntityManager();
		}
		return user;
	}

	public String getSessionToken(String number) {
		number = number + System.currentTimeMillis() + createSessionToken();
		return EncryptionUtil.encrypt(number);
	}
}
