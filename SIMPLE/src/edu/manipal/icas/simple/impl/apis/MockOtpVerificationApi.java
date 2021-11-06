package edu.manipal.icas.simple.impl.apis;

import edu.manipal.icas.simple.apis.OtpVerificationApi;

/**
 * Dummy implementation of the OTP verification API.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class MockOtpVerificationApi implements OtpVerificationApi {

	@Override
	public Boolean verifyEmailAddress(String emailAddress) {
		return true;
	}

}
