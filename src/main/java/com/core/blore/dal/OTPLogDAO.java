package com.core.blore.dal;

import javax.persistence.EntityManager;

import com.blore.bean.OTPLog;

public class OTPLogDAO extends GenericJpaRepository<OTPLog, Long> {

	public OTPLogDAO(EntityManager eManager) {
		super(eManager);

	}

	public OTPLog getByMobileandOTP(String mobileNum, String otp) {
		OTPLog otpLog=null;
		try {
			otpLog=(OTPLog) getEntityManager().createQuery(" from OTPLog o where o.user.contactNo =? and o.otpSent=? ").setParameter(1, mobileNum).setParameter(2, otp).getSingleResult();
		} catch (Exception e) {
			otpLog=null;
			e.printStackTrace();
		}
		return otpLog;
	}

}
