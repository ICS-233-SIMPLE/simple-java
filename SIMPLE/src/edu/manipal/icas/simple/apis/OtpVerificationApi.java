package edu.manipal.icas.simple.apis;

/**
 * Interface that defines operations for a third-party email-based OTP
 * authentication service provider to implement.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface OtpVerificationApi {
	/**
	 * Verifies that the email address passed is authentic and owned by the one who
	 * has requested verification.
	 * 
	 * @param emailAddress the email to verify
	 * @return {@code true} if the email address was verified, {@code false} otherwise
	 */
	Boolean verifyEmailAddress(String emailAddress);
}
