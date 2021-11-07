package edu.manipal.icas.simple.models;

import java.io.IOException;

import edu.manipal.icas.simple.databases.PassportOfficerDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPassportOfficerDatabase;

/**
 * Superclass of all passport officers.
 *
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public abstract class PassportOfficer {

	private Integer officerId;
	protected static PassportOfficerDatabase db = MsAccessPassportOfficerDatabase.getDatabase();

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
	 */
	public abstract void processApplication();

	public static Boolean checkForId(Integer officerId) {
		if (db.passportOfficerExists(officerId)) {

			return true;

		}
		return false;

	}

	public static Boolean checkforRole(Integer officerId, PassportOfficerRole officerRole) throws IOException {
		if (officerRole == db.fetchOfficerRole(officerId)) {
			return true;

		}

		else
			return false;

	}
}
