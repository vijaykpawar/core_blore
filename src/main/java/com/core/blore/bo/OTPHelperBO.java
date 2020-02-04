package com.core.blore.bo;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import com.blore.bean.CoreRequest;
import com.blore.bean.CoreResponse;
import com.blore.bean.OTPLog;
import com.blore.bean.User;
import com.core.blore.dal.UserDAO;
import com.core.blore.utils.CoreUtils;
import com.sun.jndi.toolkit.corba.CorbaUtils;

public class OTPHelperBO extends BaseBO{

	public String generateOTP() {
        String chars = "0123456789";
        final int PW_LENGTH = 6;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++)
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        return pass.toString();
    }
	
	
	
public class OTPRes implements Serializable{
	public String otp;
	public String msg;
}

	public CoreResponse getOTPforUser(CoreRequest coreRequest) {
		//core logic to save otp log against user phone number in database 
		CoreResponse coreResponse=new CoreResponse();
		UserBO userBO=new UserBO();
		OTPLog otpLog=new OTPLog();	
		try {
			User user=userBO.getUserForRegistration(coreRequest);
			getEntityManager().getTransaction().begin();
			User udb=new UserDAO(getEntityManager()).findById(user.getUserId());
			otpLog.setUser(udb);
			otpLog.setOptpSent(generateOTP());
			otpLog.setOtpSentTime(new Date(System.currentTimeMillis()));
			getEntityManager().persist(otpLog);
			getEntityManager().getTransaction().commit();
			coreResponse.setMsgToClient(1200);
			OTPRes res=new OTPRes();
			res.otp=otpLog.getOptpSent();
			res.msg=CoreUtils.OTP_SUCCESS;
			coreResponse.setData(res);
		} catch (Exception e) {
			coreResponse.setMsgToClient(1500);
			e.printStackTrace();
		} finally{
			closeEntityManager();
		}
		return coreResponse;
	}
	
}
