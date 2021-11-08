package edu.manipal.icas.simple.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.manipal.icas.simple.databases.ApplicationDatabase;
import edu.manipal.icas.simple.databases.CitizenDatabase;
import edu.manipal.icas.simple.databases.PassportOfficerDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessApplicationDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessCitizenDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPassportOfficerDatabase;
import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.models.application.ApplicationFactory;

/**
 * Superclass of all passport officers.
 *
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 * @author Mehsheed (syed.ahmed2@learner.manipal.edu)
 *
 */
public abstract class PassportOfficer {

	private Integer officerId;
	protected static final PassportOfficerDatabase db = MsAccessPassportOfficerDatabase.getDatabase();

	public PassportOfficer(Integer officerId) {
		this.officerId = officerId;
	}

	/**
	 * Gets the officer ID of the passport officer.
	 *
	 * @return the officer ID
	 */
	public Integer getOfficerId() {
		return officerId;
	}

	/**
	 * Method that defines how each passport officer handles passport applications.
	 * 
	 * @param application application to be processed
	 */
	public abstract void processApplication(Application application);

	/**
	 * Authenticates a passport officer given their ID and role
	 * 
	 * @param officerId   unique ID of the officer
	 * @param officerRole role played by the officer
	 * @return
	 */
	public static Boolean authenticate(Integer officerId, PassportOfficerRole officerRole) {
		try {
			return db.passportOfficerExists(officerId) && officerRole == db.fetchOfficerRole(officerId);
		} catch (IOException e) {
			return false;
		}

	}

	public List<Application> getApplications() {
		List<Application> applications = new ArrayList<>();
		try {
			List<Integer> appIds = db.fetchApplications(officerId);
			for (Integer appId : appIds) {
				applications.add(ApplicationFactory.getInstance().getApplication(appId));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applications;
	}
}
