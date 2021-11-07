package edu.manipal.icas.simple.models;

import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.models.application.ApplicationStatus;

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

	public void stampPassport() {
	}

	@Override
	public void processApplication(Application application) {
		processApplication(application, false);
	}
	
	public void processApplication(Application application, Boolean granted) {
		application.setStatus(granted ? ApplicationStatus.PENDING_ADDRESS_VERIFICATION : ApplicationStatus.REJECTED);
	}
	
	public void requestAddressVerification(Application application) {
		
	}

}
