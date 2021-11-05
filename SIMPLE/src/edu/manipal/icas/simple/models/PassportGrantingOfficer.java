package edu.manipal.icas.simple.models;

/**
 * Class that represents a passport granting officer. <br/>
 * The passport granting officer reviews the entire application and has the
 * power to either approve or reject the citizen's applicaiton. The PGO can also
 * request police verification.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class PassportGrantingOfficer extends PassportOfficer {

	public PassportGrantingOfficer(Integer officerId) {
		super(officerId);
	}

	private void requestAddressVerification(Citizen citizen) {
	}

	public void stampPassport() {
	}

	@Override
	public void processApplication() {
	}

}
