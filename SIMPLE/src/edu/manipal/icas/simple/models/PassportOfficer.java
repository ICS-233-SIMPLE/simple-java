package edu.manipal.icas.simple.models;

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
	protected PassportOfficerDatabase db;

	public PassportOfficer(Integer officerId) {
		this.officerId = officerId;
		db = MsAccessPassportOfficerDatabase.getDatabase();
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
}
