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

	public static Boolean authenticate(Integer officerId, PassportOfficerRole officerRole) {
		try {
			return db.passportOfficerExists(officerId) && officerRole == db.fetchOfficerRole(officerId);
		} catch (IOException e) {
			return false;
		}

	}
}
